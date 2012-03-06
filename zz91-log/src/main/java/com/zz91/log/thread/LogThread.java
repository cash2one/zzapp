/**
 * Copyright 2011 ASTO.
 * All right reserved.
 * Created on 2011-10-13
 */
package com.zz91.log.thread;

import org.apache.log4j.Logger;

import com.zz91.log.service.LogService;
import com.zz91.util.log.LogInfo;

/**
 * @author Leon
 * 
 */
public class LogThread implements Runnable {

	private static Logger LOG=Logger.getLogger(LogThread.class);
	private LogInfo logInfo;

	public LogThread() {

	}

	public LogThread(LogInfo log) {
		this.logInfo = log;
	}
	
	@Override
	public void run() {
		LogService.getInstance().insertLog(logInfo);
		LOG.debug("LogService.getInstance().insertLog(logInfo) run success!");
		
	}

}
