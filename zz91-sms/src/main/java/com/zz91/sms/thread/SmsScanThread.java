package com.zz91.sms.thread;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.service.smslog.SmsLogService;

@Service
public class SmsScanThread extends Thread{
	
	public final static Queue<SmsLog> smsqueue=new ArrayBlockingQueue<SmsLog>(100);
	
	public final static Map<String, Long> map=new ConcurrentHashMap<String, Long>();
	
	public static boolean runSwitch=true;
	@Resource
	private SmsLogService smsLogService;
	
	public SmsScanThread(){
		
	}
	@SuppressWarnings("static-access")
	public void run(){
		while(runSwitch){
			if(smsqueue.size()<50){
				List<SmsLog> sms=smsLogService.queryLogs(50);
				if(sms !=null && sms.size()>0){
					for(SmsLog smsLog:sms){
						if (smsqueue.size()>=50) {
							break;
						}
						smsqueue.add(smsLog);
						smsLogService.updateSuccess(smsLog.getId(), smsLogService.SEND_PROCESS);
					}
				}
			}
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				
			}
		}
	}
}
