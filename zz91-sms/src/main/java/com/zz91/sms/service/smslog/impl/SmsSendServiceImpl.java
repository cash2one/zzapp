package com.zz91.sms.service.smslog.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import com.zz91.sms.dao.smslog.SmsLogDao;
import com.zz91.sms.dao.template.TemplateDao;
import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.domain.Template;
import com.zz91.sms.service.smslog.SmsSendService;

@Component("smsSendService")
public class SmsSendServiceImpl implements SmsSendService{
	
	@Resource
	private SmsLogDao smsLogDao;
	@Resource
	private TemplateDao templateDao;

	@Override
	public Integer sendSms(SmsLog sms){
		sms.setContent(buildSmsContent(sms.getTemplateCode(), sms.getSmsParameter()));
		sms.setSendStatus(0);
		sms.setReceiver("");
		sms.setTemplateCode("00");
		sms.setGatewayCode("000");
		sms.setPriority(0);
		sms.setGmtSend(new Date());
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
		sms.setContent(buildSmsContent(getTemplateContentByTemplateCode(code),sms.getSmsParameter()));
		Template template=templateDao.queryTemplateByCode(code);
		if(template==null){
			return null;
		}
		sms.setSendStatus(0);
		sms.setGmtSend(new Date());
//		sms.GatewayCode();
		sms.setTemplateCode(code);
//		sms.setPriority();
//		sms.setReceiver();
		return smsLogDao.insert(sms);
	}
	private String getTemplateContentByTemplateCode(String templateCode) {
		Template temp =templateDao.queryTemplateByCode(templateCode);
		if (temp != null && temp.getContent() != null) {
			return temp.getContent();
		} else {
			return "";
		}
	}
}