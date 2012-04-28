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
	
	public final static Queue<SmsLog> smsqueue=new ArrayBlockingQueue<SmsLog>(50);
	
	public final static Map<String, Long> map=new ConcurrentHashMap<String, Long>();
	
	public static boolean runSwitch=true;
	@Resource
	private SmsLogService smsLogService;
	
	public SmsScanThread(){
		smsqueue.clear();
	}
	@SuppressWarnings("static-access")
	public void run(){
		while(runSwitch){
			if(smsqueue.size()<10){
				List<SmsLog> smsList=smsLogService.queryLogs(10);
				if(smsList !=null && smsList.size()>0){
					for(SmsLog smsLog:smsList){
						smsLogService.updateSuccess(smsLog.getId(), smsLogService.SEND_PROCESS);
						smsqueue.add(smsLog);
					}
				}
			}
			try{
				Thread.sleep(5000);
			}catch(InterruptedException e){
				
			}
		}
	}
}
