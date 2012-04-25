package com.zz91.sms.thread;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.service.smslog.SmsLogService;
import com.zz91.sms.service.smslog.SmsSendService;
@Service
public class SmsDisTributeThread extends Thread{
	public static boolean runSwitch=true;
	
	@Resource
	SmsSendService smsSendService;
	@Resource
	SmsLogService smsLogService;
	
	public SmsDisTributeThread(){
		
	}
	public void run(){
		while(runSwitch){
			SmsLog smsLog=SmsScanThread.smsqueue.peek();
			if(smsLog!=null){
				int queueSize=ControlThread.mainPool.getQueue().size();
				if(queueSize<60){
					ControlThread.excute(new SmsSendThread(SmsScanThread.smsqueue.poll(),smsLogService,smsSendService));
				}else if(queueSize==60 && ControlThread.mainPool.getActiveCount()<ControlThread.mainPool.getMaximumPoolSize()){
					ControlThread.excute(new SmsSendThread(SmsScanThread.smsqueue.poll(),smsLogService,smsSendService));
				}else{
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						
					}
				}
			}else{
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					
				}
			}
		}
	}
	public static void main(String[] args) {
		
	}

}
