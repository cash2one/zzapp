/**
 * Copyright 2011 ASTO.
 * All right reserved.
 * Created on 2011-10-13
 */
package com.zz91.mail.thread;

import com.zz91.mail.domain.MailInfoDomain;
import com.zz91.mail.service.MailInfoService;
import com.zz91.mail.service.MailSendService;

/**
 * 邮件发送线程，将邮件信息提交给邮件服务器
 * 
 * @author kongsj
 * @email kongsj@zz91.net
 * @date 2011-11-23
 */

public class MailSendThread extends Thread {

	private MailInfoDomain mailInfo;
	private MailInfoService mailInfoService;
	private MailSendService mailSendService;

	public MailSendThread() {
		
	}

	public MailSendThread(MailInfoDomain mailInfo,
			MailInfoService mailInfoService, MailSendService mailSendService) {
//		super("thread-send-"+mailInfo.getId());
		this.mailInfo = mailInfo;
		this.mailSendService = mailSendService;
		this.mailInfoService = mailInfoService;
	}

	@Override
	public void run() {

		String receiver = mailInfo.getReceiver();
		String tmp = receiver.substring(receiver.indexOf("@") + 1, receiver
				.length());

		long now = System.currentTimeMillis();
//		long start = System.currentTimeMillis();
		// 得到计划发送时间
		Long planSendTime = MailScanThread.getPlanSendTime(tmp, 2000, now);

		while (now < planSendTime) { // 如果还没有到计划发送时间，则不发送邮件
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			now = System.currentTimeMillis();
		}

		Integer sendStatus = mailSendService.doSendMail(mailInfo);
		mailInfoService.updateComplete(mailInfo.getId(), sendStatus);
//
//		System.out.println("发送邮件："
//				+ tmp
//				+ " 时间:"
//				+ DateUtil.toString(new Date(now), "HH:mm:ss")
//				+ " 计划发送时间："
//				+ DateUtil.toString(new Date(planSendTime), "yyyy-MM-dd HH:mm:ss") 
//				+ " 等待时间：" + (now - start)
//				+ "ms");
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
