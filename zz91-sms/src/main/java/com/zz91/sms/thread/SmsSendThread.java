package com.zz91.sms.thread;
import javax.annotation.Resource;

import com.zz91.sms.common.ZZSms;
import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.service.gateway.GatewayService;
import com.zz91.sms.service.smslog.SmsLogService;
import com.zz91.sms.service.smslog.SmsSendService;
import com.zz91.sms.util.ClassHelper;


public class SmsSendThread extends Thread {
	
	private SmsLog smsLog;
	private SmsLogService smsLogService;
	private SmsSendService smsSendService;
	@Resource
	private GatewayService gatewayService;
	public SmsSendThread() {
		
	}

	public SmsSendThread(SmsLog smsLog,
			SmsLogService smsLogService, SmsSendService smsSendService) {
		this.smsLog = smsLog;
		this.smsLogService = smsLogService;
		this.smsSendService = smsSendService;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		ZZSms sms = (ZZSms) gatewayService.CACHE_GATEWAY.get(smsLog.getGatewayCode());
		Integer sendStatus = 2;
		if(sms!=null){
			sendStatus = sms.send(smsLog.getReceiver(), smsLog.getContent());
		}
		smsLogService.updateSuccess(smsLog.getId(), sendStatus);
	}
}

