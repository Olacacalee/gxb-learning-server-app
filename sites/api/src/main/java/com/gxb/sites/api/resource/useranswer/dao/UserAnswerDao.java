package com.gxb.sites.api.resource.useranswer.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.useranswer.UserAnswer;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

/**
 * Created by He on 2016/10/20.
 */
@LcmsRepository
public interface UserAnswerDao extends BasicDao<UserAnswer> {


    UserAnswer getByUnionId(String unionId);

    void updateUserIdByUnionId(@Param(value = "userId") Long userId,@Param(value = "unionId") String unionId);
}
