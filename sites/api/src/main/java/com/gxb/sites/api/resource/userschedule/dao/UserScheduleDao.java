package com.gxb.sites.api.resource.userschedule.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.userschedule.UserSchedule;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by Administrator on 2016-10-14.
 */
@LcmsRepository
public interface UserScheduleDao extends BasicDao<UserSchedule> {
      int scheduleIsExist(@Param(value = "unionid") String unionid, @Param(value = "courceid") long courceid);
      int updateUserSchedule(@Param(value = "id") int id,@Param(value = "schedule") int schedule);
      int saveUserSchedule(@Param(value = "userid") long userid, @Param(value = "courceid") long courceid, @Param(value = "schedule") int schedule,@Param(value = "unionid") String unionid);

      UserSchedule checkLastChapter(Long userId);

      List<UserSchedule> getByUnionId(String unionId);

      void updateUserIdByUnionId(@Param(value = "userId")Long userId,@Param(value = "unionId") String unionId);

      void updateUserScheduleToDeleted(String unionid);

}
