/**
 * Project name: zz91-mail
 * File name: InitSystem.java
 * Copyright: 2005-2011 ASTO Info TechCo.,Ltd. All rights reserved
 */
package com.zz91.sms.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zz91.sms.service.GatewayService;
import com.zz91.sms.service.SmsLogService;
import com.zz91.util.cache.MemcachedUtils;

/**
 * @author kongsj
 * @email kongsj@zz91.net
 * @date 2011-10-26
 */
@Service
public class InitSystem {
	
	@Resource
	private GatewayService gatewayService;
	@Resource
	private SmsLogService smsLogService;
	
	final static Logger LOG=Logger.getLogger(InitSystem.class);
	
	public void startup() throws ServletException {
		//初始化 memcached
		MemcachedUtils.getInstance().init("web.properties");
		
		//初始化 项目数据
		initBaseConfig();
		
		//初始化发送网管帐号
		gatewayService.initGateway();
		
		if(! smsLogService.shutdownRecovery(SmsLogService.SEND_PROCESS, SmsLogService.SEND_READY)){
			LOG.error("sms was not recovery success");
			throw new ServletException("sms was not recovery success.");
		}
		
	}

	public void destroy() {

	}
	
	public void initBaseConfig() {
		LOG.info(">>>>>>>>Init system properties cache start...");
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("web.properties");
		Properties p = new Properties();

		try {
			p.load(inputStream);
		} catch (IOException e1) {
			LOG.error("error read web.properties", e1);
		}

		String tmp = null;
		for (Object key : p.keySet()) {
			tmp = String.valueOf(key);
			if (tmp != null && tmp.startsWith("debug")) {
				MemcachedUtils.getInstance().getClient().set(String.valueOf(key), 0, String.valueOf(p.get(key)));
			}
		}

		LOG.info(">>>>>>>>Init system properties cache end...");
	}
}
