package com.gxb.sites.api.resource.user.service;

import com.gxb.modules.domain.result.ResultObject;
import com.gxb.modules.domain.user.User;
import com.gxb.sites.api.config.constant.TransactionConstant;
import com.gxb.sites.api.resource.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by xing on 2017/9/21.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(TransactionConstant.LCMS)
    public ResultObject login(User user) {
        User dbUser = userDao.getByUsername(user.getUsername());
        if(dbUser==null){
            return new ResultObject("0","用户名不存在",null);
        }
        if(dbUser.getPassword().equals(user.getPassword())){
            String uuid = UUID.randomUUID().toString();
            dbUser.setUuid(uuid);
            dbUser.setPassword(null);
            dbUser.setCreatedAt(null);
            dbUser.setUpdatedAt(null);
            return new ResultObject("1","登录成功",dbUser);
        }
        return new ResultObject("0","密码错误",null);
    }

}
