package com.zz91.sms.thread;

<<<<<<< HEAD
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

=======
>>>>>>> origin/feature-sms
import com.zz91.sms.common.ZZSms;
import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.service.GatewayService;
import com.zz91.sms.service.SmsLogService;

@Service
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
<<<<<<< HEAD
		ZZSms sms = (ZZSms) gatewayService.CACHE_GATEWAY.get(smsLog.getGatewayCode());
		Integer sendStatus = smsLogService.SEND_PROCESS;
		do{
			if (sms != null) {
				break;
			}
			
=======
		ZZSms sms = (ZZSms) GatewayService.CACHE_GATEWAY.get(smsLog.getGatewayCode());
		Integer sendStatus = SmsLogService.SEND_PROCESS;
		if (sms != null) {
>>>>>>> origin/feature-sms
//			sendStatus = sms.send(smsLog.getReceiver(), smsLog.getContent());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
//			System.out.print("send sms:"+smsLog.getReceiver()+";");
			
		}while(false);
		smsLogService.updateSuccess(smsLog.getId(), sendStatus);
	}
}
