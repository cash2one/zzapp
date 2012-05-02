package com.zz91.sms.thread;

import com.zz91.sms.common.ZZSms;
import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.service.GatewayService;
import com.zz91.sms.service.SmsLogService;

public class SmsSendThread extends Thread {

	private SmsLog smsLog;
	private SmsLogService smsLogService;

	public SmsSendThread() {

	}

	public SmsSendThread(SmsLog smsLog, SmsLogService smsLogService) {
		this.smsLog = smsLog;
		this.smsLogService = smsLogService;
	}

	@Override
	public void run() {
		ZZSms sms = (ZZSms) GatewayService.CACHE_GATEWAY.get(smsLog.getGatewayCode());
		Integer sendStatus = SmsLogService.SEND_PROCESS;
		if (sms != null) {
//			sendStatus = sms.send(smsLog.getReceiver(), smsLog.getContent());
		}
		smsLogService.updateSuccess(smsLog.getId(), sendStatus);
	}
}
