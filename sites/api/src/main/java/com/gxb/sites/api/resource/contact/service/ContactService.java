package com.gxb.sites.api.resource.contact.service;

import com.gxb.modules.core.domain.FilterDomain;
import com.gxb.modules.domain.contact.Contact;
import com.gxb.modules.domain.result.ResultObject;
import com.gxb.sites.api.resource.contact.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xing on 2017/9/30.
 */
@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    public ResultObject saveContact(Contact contact){
        contactDao.save(contact);
        return new ResultObject("1","保存成功",contact.getContactId());
    }

    public ResultObject getContactList(FilterDomain<Contact> filterDomain){
        List<Contact> list = contactDao.getContactList(filterDomain);
        filterDomain.setDataList(list);
        return new ResultObject("1","success",filterDomain);
    }

    public ResultObject updateContact(Contact contact){
        contactDao.update(contact);
        return new ResultObject("1","保存成功",null);
    }

    public ResultObject getContactById(Long contactId) {
        return new ResultObject("1","success",contactDao.getById(contactId));
    }

    public ResultObject deleteContact(Long contactId) {
        contactDao.delete(contactId);
        return new ResultObject("1","删除成功",null);
    }
}
