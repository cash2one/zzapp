package com.zz91.sms.config;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zz91.sms.service.smslog.SmsLogService;
import com.zz91.sms.thread.ControlThread;
import com.zz91.sms.thread.SmsScanThread;
import com.zz91.sms.thread.SmsSendThread;

@Component("initThread")
public class InitThread extends HttpServlet{
	private static final long serialVersionUID = 9188497441746376931L;
	
	final static Logger LOG=Logger.getLogger(InitThread.class);
	
	@Resource
	private SmsScanThread smsScanThread;
	@Resource
	private SmsLogService smsLogService;
	@Resource
	private ControlThread controlThread;
	@Resource
	private SmsSendThread smsSendThread;
	
	public void startup() throws ServletException{
		if(! smsLogService.shutdownRecovery(SmsLogService.SEND_PROCESS, SmsLogService.SEND_READY)){
			LOG.error("sms was not recovery success");
			throw new ServletException("sms was not recovery success.");
		}
		controlThread.start();
		smsScanThread.start();
		smsSendThread.start();
	}
	public void destroy(){
		
	}

}
