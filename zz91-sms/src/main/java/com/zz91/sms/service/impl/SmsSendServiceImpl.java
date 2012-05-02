package com.zz91.sms.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import com.zz91.sms.dao.SmsLogDao;
import com.zz91.sms.dao.TemplateDao;
import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.domain.Template;
import com.zz91.sms.service.SmsSendService;

@Component("smsSendService")
public class SmsSendServiceImpl implements SmsSendService{
	
	@Resource
	private SmsLogDao smsLogDao;
	@Resource
	private TemplateDao templateDao;

	@Override
	public Integer sendSms(SmsLog sms){
		sms.setContent(buildSmsContent(sms.getContent(), sms.getSmsParameter()));
		sms.setSendStatus(0);
	return smsLogDao.insert(sms);
}

	private String buildSmsContent(String smsContent,Map<String, Object> map) {
		StringWriter w = new StringWriter();
		VelocityContext c = new VelocityContext(map);
		try {
			Velocity.evaluate(c, w, "SmsContent", smsContent);
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = w.toString();
		if (str != null && str.length() > 0) {
			return w.toString();
		} else {
			return "";
		}
	}

	@Override
	public Integer sendSmsByCode(SmsLog sms) {
		String code=sms.getTemplateCode();
		Template template = templateDao.queryTemplateByCode(code);
		sms.setContent(buildSmsContent(template.getContent() + template.getSigned(), sms.getSmsParameter()));
		
		sms.setSendStatus(0);
		sms.setTemplateCode(code);
		return smsLogDao.insert(sms);
	}
	
}