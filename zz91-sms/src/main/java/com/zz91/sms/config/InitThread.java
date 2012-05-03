package com.zz91.sms.config;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zz91.sms.thread.ControlThread;
import com.zz91.sms.thread.SmsScanThread;
import com.zz91.sms.thread.SmsSendThread;

@Component("initThread")
public class InitThread {
	
	
	
	@Resource
	private SmsScanThread smsScanThread;
	@Resource
	private ControlThread controlThread;
	@Resource
	private SmsSendThread smsSendThread;
	
	public void startup(){
		controlThread.start();
		smsScanThread.start();
		smsSendThread.start();
	}
	
	public void destroy(){
		
	}

}
