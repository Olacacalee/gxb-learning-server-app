/**
 *  Copyright (c)  2014-2020 Gaoxiaobang, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Gaoxiaobang, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Gaoxiaobang.
 */
package com.gxb.modules.core.domain;

import org.junit.Test;

/**
 * 
 * @author lh
 * @date 2015年10月29日
 */
public class PagingDomainTest {

	@Test
	public void testPagingCurPage() {
		PagingDomain paging = new PagingDomain();
		paging.setCurPage(5);
		paging.setPageSize(20);
		paging.setTotal(222);
//		assertThat(paging.getCurPage()).isEqualTo(5);
//
//		paging.setCurPage(-1);
//		assertThat(paging.getCurPage()).isEqualTo(1);
//
//		paging.setCurPage(33);
//		assertThat(paging.getCurPage()).isEqualTo(12);
	}
	
	@Test
	public void testPagingTotal() {
		PagingDomain paging = new PagingDomain();
		paging.setCurPage(15);
		paging.setTotal(101);
//		assertThat(paging.getCurPage()).isEqualTo(11);
//
//		paging.setTotal(133);
//		assertThat(paging.getCurPage()).isEqualTo(11);
	}

}
