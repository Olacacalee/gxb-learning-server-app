package com.gxb.sites.api.resource.userinfo.dao;

import com.gxb.modules.core.dao.BasicDao;


import com.gxb.modules.domain.electiveuesrinfo.ElectiveUserInfo;
import com.gxb.modules.domain.permission.UserInfo;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;


/**
 * Created by Administrator on 2016-10-14.
 */
@LcmsRepository
public interface UserInfoDao extends BasicDao<ElectiveUserInfo> {
   ElectiveUserInfo getUserInfoByUserId(int userId);
   ElectiveUserInfo getUserInfoByUserUnionID(String unionid);
   int updateUserLock(@Param(value = "lock") int lock,@Param(value = "id")long id);
   int updateUserLockByUnionID(@Param(value = "lock") int lock,@Param(value = "unionid")String unionid);
   int getUserPersonalityType(int userid);
   int getUserPersonalityTypeByUnionID(String unionid);
   int findUserInfoByUserId(long userid);
   void applyScore(Long userId);
   int saveUserUnionid(String unionid);
   UserInfo findUserByUnionid(String unionid);
   ElectiveUserInfo findElectiveUserInfo(long userid);
   Long getUserIDInfoByUserUnionID(String unionid);
   int getUserLockByUnionID(String unionid);
   void updateUserIdByUnionId(@Param(value = "userId")Long userId,@Param(value = "unionid") String unionid);
   Long getIDInfoByUserUnionID(String unionid);

   void saveUserInfoByUnionId(ElectiveUserInfo userInfo);

   void updateReportTime(ElectiveUserInfo userinfo);

   int findPersonalityByUnionId(String unionid);


}
