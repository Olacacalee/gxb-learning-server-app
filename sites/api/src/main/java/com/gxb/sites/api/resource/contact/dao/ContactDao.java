package com.gxb.sites.api.resource.contact.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.core.domain.FilterDomain;
import com.gxb.modules.domain.contact.Contact;
import com.gxb.sites.api.annotation.LcmsRepository;

import java.util.List;

/**
 * Created by xing on 2017/9/30.
 */
@LcmsRepository
public interface ContactDao extends BasicDao<Contact> {

    List<Contact> getContactList(FilterDomain<Contact> filterDomain);
}
