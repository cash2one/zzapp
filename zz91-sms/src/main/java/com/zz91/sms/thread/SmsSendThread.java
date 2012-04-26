package com.zz91.sms.thread;
import java.util.Map;

import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.service.smslog.SmsLogService;
import com.zz91.sms.service.smslog.SmsSendService;


public class SmsSendThread extends Thread {
	
	private SmsLog smsLog;
	private SmsLogService smsLogService;
	private SmsSendService smsSendService;

	public SmsSendThread() {
		
	}

	public SmsSendThread(SmsLog smsLog,
			SmsLogService smsLogService, SmsSendService smsSendService) {
		this.smsLog = smsLog;
		this.smsLogService = smsLogService;
		this.smsSendService = smsSendService;
	}

	@Override
	public void run() {
//		Integer sendStatus = smsSendService.sendSms(smsLog);
//		Map.get(smsLog.getGatewayCode()).send(smsLog.getcontent,smsLog,getmobile)
		smsLogService.updateSuccess(smsLog.getId(), 1);
	}
}

