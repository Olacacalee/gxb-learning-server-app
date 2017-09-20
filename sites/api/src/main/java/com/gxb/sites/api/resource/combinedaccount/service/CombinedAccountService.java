package com.gxb.sites.api.resource.combinedaccount.service;

import com.gxb.modules.constants.DomainConstants;
import com.gxb.modules.core.exception.ServiceException;
import com.gxb.modules.domain.user.CombinedAccount;
import com.gxb.modules.domain.user.User;
import com.gxb.sites.api.resource.combinedaccount.dao.CombinedAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2016-10-14.
 */
@Service
public class CombinedAccountService {

    @Autowired
    private CombinedAccountDao combinedAccountDao;

    public void bindCombinedAccountByUnionId(String loginKey, String keyType, User user, String unionId) {//获取用户绑定信息
        CombinedAccount combinedAccount = new CombinedAccount();
        combinedAccount.setLoginKey(loginKey);
        combinedAccount.setKeyType(keyType);
        combinedAccount.setUnionId(unionId);
        //通过unionId查询表中是否有数据,如果有，则要么是已删除状态，要么就是只有unionId，还未注册，userId为空，是bigbang的用户
        //如果没有，则绑定手机与微信，在combined_account表中新增数据
        List<CombinedAccount> combinedAccounts = combinedAccountDao.getByLoginKeyOrUnionId(combinedAccount);
        CombinedAccount combinedAccountInDB = combinedAccounts.size() > 0 ? combinedAccounts.get(0) : null;

        //如果绑定信息存在则修改,不存在则新增
        if (combinedAccountInDB != null) {
            if (Objects.equals(combinedAccountInDB.getDeleteFlag(), DomainConstants.DELETE_FLAG_AVAILABLE) && combinedAccountInDB.getUserId()!=null) {
                throw new ServiceException("combined_account is available");
            }

            combinedAccountInDB.setUserId(user.getUserId());
            combinedAccount.setUnionId(unionId);
            combinedAccountInDB.setLoginKey(loginKey);
            combinedAccountInDB.setDeleteFlag(DomainConstants.DELETE_FLAG_AVAILABLE);
            if (combinedAccountDao.update(combinedAccountInDB) <= 0) {
                throw new ServiceException("binding openId error");
            }
        } else {
            combinedAccount.setUnionId(unionId);
            combinedAccount.setLoginKey(loginKey);
            combinedAccount.setUserId(user.getUserId());
            combinedAccount.setDeleteFlag(DomainConstants.DELETE_FLAG_AVAILABLE);
            if (combinedAccountDao.save(combinedAccount) <= 0) {
                throw new ServiceException("binding openId error");
            }
        }
    }
}
