/**
 * Project name: zz91-mail
 * File name: MailServiceTest.java
 * Copyright: 2005-2011 ASTO Info TechCo.,Ltd. All rights reserved
 */
package com.zz91.mail.test;

import java.util.Date;

import javax.annotation.Resource;

import com.zz91.mail.domain.MailInfoDomain;
import com.zz91.mail.service.MailInfoService;

/**
 * @author kongsj
 * @email kongsj@zz91.net
 * @date 2011-11-10
 */
public class MailInfoServiceTest extends BaseServiceTestCase {
    @Resource
    private MailInfoService mailInfoService;

    private int test_insert() {
        MailInfoDomain mto = new MailInfoDomain();
        mto.setContent("content");
        mto.setEmailTitle("emailTitle");
        mto.setGmtPost(new Date());
        mto.setReceiver("receiver@zz91.net");
        mto.setSender("sender@zz91.com");
        mto.setSendStatus(1);
        mto.setTemplateId("1");
        int i = mailInfoService.insert(mto);
        assertTrue(i > 0);
        return i;
    }

    public void test_select() {
        int i = test_insert();
        MailInfoDomain mto = mailInfoService.selectById(i);
        assertEquals("select error", 1, (int) mto.getSendStatus());
        test_delete(i);
    }

    public void test_update() {
        int i = test_insert();
        MailInfoDomain mto = new MailInfoDomain();
        mto.setSendStatus(2);
        mto.setId(i);
        mailInfoService.update(mto);
        mto = mailInfoService.selectById(i);
        assertEquals("update error", 2, (int) mto.getSendStatus());
        test_delete(i);
    }

    private int test_delete(Integer id) {
        return mailInfoService.deleteById(id);
    }
}
