package com.zz91.sms.thread;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.service.SmsLogService;

@Service
public class SmsDisTributeThread extends Thread {
	public static boolean runSwitch = true;

	@Resource
	private SmsLogService smsLogService;

	public SmsDisTributeThread() {

	}

	public void run() {
		while (runSwitch) {
			SmsLog smsLog = SmsScanThread.smsqueue.peek();
			if (smsLog != null) {
				int queueSize = ControlThread.mainPool.getQueue().size();
				if (queueSize <= 60) {
					ControlThread.excute(new SmsSendThread(
							SmsScanThread.smsqueue.poll(), smsLogService));
				} else {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {

					}
				}
			} else {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {

				}
			}
		}
	}
}
