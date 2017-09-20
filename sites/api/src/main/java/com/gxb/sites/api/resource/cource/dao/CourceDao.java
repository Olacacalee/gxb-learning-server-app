package com.gxb.sites.api.resource.cource.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.cource.Cource;
import com.gxb.modules.domain.user.User;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-10-14.
 */
@LcmsRepository
public interface CourceDao extends BasicDao<Cource> {
    List<Cource> getCourceByparentId(@Param(value = "userid") int userid, @Param(value = "id") int id);
    List<Cource> getCourceByUnionId(@Param(value = "unionid") String unionid, @Param(value = "id") int id);
    Cource getCourceByPidType(@Param(value = "courceid") int courceid, @Param(value = "personalitytype") int personalitytype);
    List<Cource> getCourceByParentId( int courceid);
    Cource getTitleByCourceid(int courceid);


    Cource getCourceById(int id);

}
