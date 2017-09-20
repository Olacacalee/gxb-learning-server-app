/**
 *  Copyright (c)  2014-2020 Gaoxiaobang, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Gaoxiaobang, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Gaoxiaobang.
 */
package com.gxb.sites.api.db.plugin;

import com.google.common.base.CaseFormat;
import com.gxb.modules.core.domain.FilterDomain;
import com.gxb.modules.core.domain.PagingDomain;
import com.gxb.modules.core.domain.Sort;
import com.gxb.modules.utils.CollectionTools;
import com.gxb.modules.utils.ReflectionTools;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis paging plugin
 * 
 * @author lh
 * @date 2015年10月23日
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(PagePlugin.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object targetObject = invocation.getTarget();
		StatementHandler statementHandler = (StatementHandler) targetObject;
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		// 分离代理对象链
		while (metaObject.hasGetter("h")) {
			Object object = metaObject.getValue("h");
			metaObject = SystemMetaObject.forObject(object);
		}
		// 分离最后一个代理对象的目标类
		while (metaObject.hasGetter("target")) {
			Object object = metaObject.getValue("target");
			metaObject = SystemMetaObject.forObject(object);
		}

		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");
		if (parameterObject instanceof FilterDomain && parameterObject != null) {
			FilterDomain<?> filterDomain = (FilterDomain<?>) parameterObject;
			if (filterDomain.getAutoPageOrder()) {//是否自动分页排序,多表关联分页不进行自动分页排序
				// 分页参数作为参数对象parameterObject的一个属性
				String sql = boundSql.getSql();

			/* sort logic */
				List<Sort> sortList = filterDomain.getSortList();
				if (CollectionTools.isNotEmpty(sortList)) {
					sql = buildSortSql(sql, sortList, mappedStatement.getResultMaps());
				}

			/* paging logic*/
				PagingDomain pagingDomain = filterDomain.getPaging();
				if (pagingDomain == null) {
					pagingDomain = new PagingDomain();
				}
				filterDomain.setPagingDomain(pagingDomain);

				Connection connection = (Connection) invocation.getArgs()[0];

			/* 获取数据总数 刷新 paging domain */
				setPageParameter(sql, connection, mappedStatement, boundSql, pagingDomain);

				// 重写sql 为分页
				String pageSql = buildPageSql(sql, pagingDomain);
				metaObject.setValue("delegate.boundSql.sql", pageSql);

				// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
				metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
			}

		}
		// 传递给下一个拦截器处理
		return invocation.proceed();
	}

	/*
	 * create sort sql
	 */
	private String buildSortSql(String sql, List<Sort> sortList, List<ResultMap> resultMapList) {
		StringBuilder sortSqlSb = new StringBuilder();
		for(ResultMap resultMap : resultMapList){
			Class<?> clazz = resultMap.getType();
			
			// validate sort field
			Iterator<Sort> iterator = sortList.iterator();
			while(iterator.hasNext()){
				Sort sort = iterator.next();
				// validte field exist
				Field field = ReflectionTools.getField(clazz, sort.getSort());
				if(field == null){
					iterator.remove();
				}else{
					// convert filed format to column format
					String column = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
					
					// filter id field
					String idField = clazz.getSimpleName()+"_"+"id";
					if(idField.equalsIgnoreCase(column)){
						column = "id";
					}
					
					// append colum to sql
					sortSqlSb.append(" ").append(column).append(" ").append(sort.getOrder()).append(",");
				}
				
			}
		}
		if(sortSqlSb.length() > 0){
			int lastSeparatorIndex = sortSqlSb.lastIndexOf(",");
			if(lastSeparatorIndex > 1){
				sortSqlSb.replace(lastSeparatorIndex, lastSeparatorIndex+1, " ");
			}
			sql = sql + " order by " + sortSqlSb.toString();
		}
		return sql;
	}
	
	private String buildPageSql(String sql, PagingDomain pagingDomain) {
		// refactor paging sql
		StringBuilder pageSql = new StringBuilder(sql.length() + 40);
		pageSql.append(sql);
		pageSql.append(" limit ");
		pageSql.append(pagingDomain.getPageSize());
		pageSql.append(" offset ");
		pageSql.append(pagingDomain.getStartRow());
		return pageSql.toString();
	}
	
	/**
	 * 查询数据库总记录数,并写入PagingDomain
	 */
	private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql,
			PagingDomain pagingDomain) {
		// 记录总记录数
		String countSql = "select count(0) from (" + sql + ") as total";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			
			SqlSource sqlSource = mappedStatement.getSqlSource();
			if(sqlSource instanceof DynamicSqlSource){
				MetaObject metaObject = SystemMetaObject.forObject(mappedStatement.getSqlSource());
				SqlNode rootSqlNode = (SqlNode) metaObject.getValue("rootSqlNode");
				DynamicContext context = new DynamicContext(mappedStatement.getConfiguration(), boundSql.getParameterObject());
				rootSqlNode.apply(context);
				for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
					countBS.setAdditionalParameter(entry.getKey(), entry.getValue());
				}
			}
			
			setParameters(pstmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = pstmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
				pagingDomain.setTotal(totalCount);
			}
		} catch (SQLException e) {
			logger.error("page totals sql,catch", e);
			this.closeRsAndPstmt(rs, pstmt);
		} finally {
			this.closeRsAndPstmt(rs, pstmt);
		}

	}
	
	/**
	 * 对SQL参数(?)设值
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject)
			throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}
	
	private void closeRsAndPstmt(ResultSet rs, PreparedStatement pstmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				logger.error("page result close exception", se);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				logger.error("page prepared statement close exception", se);
			}
		}
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		// no properties
	}

}
