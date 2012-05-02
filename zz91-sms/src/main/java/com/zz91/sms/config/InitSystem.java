/**
 * Project name: zz91-mail
 * File name: InitSystem.java
 * Copyright: 2005-2011 ASTO Info TechCo.,Ltd. All rights reserved
 */
package com.zz91.sms.config;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zz91.sms.service.GatewayService;

/**
 * @author kongsj
 * @email kongsj@zz91.net
 * @date 2011-10-26
 */
@Service
public class InitSystem {
	
	@Resource
	private GatewayService gatewayService;
	public void startup() {
		//初始化发送网管帐号
		gatewayService.initGateway();
	}

	public void destroy() {

	}
}
