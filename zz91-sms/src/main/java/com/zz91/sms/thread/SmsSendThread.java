package com.zz91.sms.thread;

import org.springframework.stereotype.Service;

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

		ZZSms sms = (ZZSms) GatewayService.CACHE_GATEWAY.get(smsLog.getGatewayCode());
		Integer sendStatus = SmsLogService.SEND_FAILURE;
		do {
			// 判空 取默认网关code
			if (sms == null) {
				sms = getDefault();
			}
			// 判断手机号码是否规范,取前11位数字发送
			if(smsLog.getReceiver().length()>11){
				smsLog.setReceiver(smsLog.getReceiver().substring(0,11));
			}
			
			// 判断是否发送短信
			if (ControlThread.DEBUG) {
				System.out.println("=======调试状态短信发送:send mobile(发送目标):"+smsLog.getReceiver()+" ; send message(发送内容):"+smsLog.getContent());
			}else{
				sendStatus = sms.send(smsLog.getReceiver(), smsLog.getContent());
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			smsLogService.updateSuccess(smsLog.getId(), sendStatus);
		} while (false);
	}

	private ZZSms getDefault() {
		ZZSms obj = null;
		for (String key : GatewayService.CACHE_GATEWAY.keySet()) {
			obj = (ZZSms) GatewayService.CACHE_GATEWAY.get(key);
			if (obj != null) {
				return obj;
			}
		}
		return null;
	}
}
