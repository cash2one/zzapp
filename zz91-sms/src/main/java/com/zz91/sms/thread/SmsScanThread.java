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
import com.zz91.sms.service.smslog.SmsSendService;

@Service
public class SmsScanThread extends Thread{
	
	public final static Queue<SmsLog> smsqueue=new ArrayBlockingQueue<SmsLog>(100);
	
	public final static Map<String, Long> map=new ConcurrentHashMap<String, Long>();
	
	public static boolean runSwitch=true;
	@Resource
	private SmsLogService smsService;
	@Resource
	private SmsSendService smsSendService;
	
	public SmsScanThread(){
		
	}
	public void run(){
		while(runSwitch){
			if(smsqueue.size()<50){
				List<SmsLog> sms=smsService.queryLogs(50);
				if(sms !=null && sms.size()>0){
					for(SmsLog smsLog:sms){
						if (smsqueue.size()>=50) {
							break;
						}
						smsqueue.add(smsLog);
					}
				}
			}
			try{
				Thread.sleep(10000);
			}catch(InterruptedException e){
				
			}
		}
	}
	public synchronized static long getPlanSendTime(String domain,long inteval,long now){
		Long lst=map.get(domain);
		if(lst==null){
			lst=0l;
		}
		if(now>lst.longValue()){
			map.put(domain, now+inteval);
		}else{
			lst=lst+inteval;
			map.put(domain, lst);
		}
		return lst;
	}

}
