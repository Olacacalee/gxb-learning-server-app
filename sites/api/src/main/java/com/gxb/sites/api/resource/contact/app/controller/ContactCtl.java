package com.gxb.sites.api.resource.contact.app.controller;

import com.gxb.modules.core.domain.FilterDomain;
import com.gxb.modules.core.domain.ResultObj;
import com.gxb.modules.domain.contact.Contact;
import com.gxb.modules.domain.result.ResultObject;
import com.gxb.sites.api.resource.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xing on 2017/9/30.
 */
@Controller
public class ContactCtl {

    @Autowired
    private ContactService contactService;

    /**
     * 保存联系人
     * @param contact
     * @return
     */
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject saveContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

    /**
     * 获取联系人
     * @param filterDomain
     * @return
     */
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject getContactList(FilterDomain<Contact> filterDomain){
        return contactService.getContactList(filterDomain);
    }

    /**
     * 更新联系人
     * @param contact
     * @return
     */
    @RequestMapping(value = "/contact", method = RequestMethod.PUT)
    @ResponseBody
    public ResultObject updateContact(@RequestBody Contact contact){
        return contactService.updateContact(contact);
    }

    /**
     * 获取联系人详情
     * @param contactId
     * @return
     */
    @RequestMapping(value = "/contact/contactId/{contactId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject getContactById(@PathVariable Long contactId){
        return contactService.getContactById(contactId);
    }

    /**
     * 删除联系人
     * @param contactId
     * @return
     */
    @RequestMapping(value = "/contact/contactId/{contactId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultObject deleteContact(@PathVariable Long contactId){
        return contactService.deleteContact(contactId);
    }

}
