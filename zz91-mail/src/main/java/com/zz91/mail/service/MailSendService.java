package com.zz91.mail.service;

import com.zz91.mail.domain.MailInfoDomain;

public interface MailSendService {
    Integer sendEmail(MailInfoDomain jto);

    public Integer sendEmailByUsename(MailInfoDomain jto);

    public Integer sendEmailByCode(MailInfoDomain jto);

    public Integer sendEmailForThread(MailInfoDomain sto);
    
    public Integer doSendMail(MailInfoDomain sto);

}
