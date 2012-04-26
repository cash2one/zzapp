package com.zz91.sms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zz91.sms.service.smslog.SmsLogService;

@Controller
public class SMSController extends BaseController{
	
	@Resource
	private SmsLogService smsLogService;
	
	@RequestMapping
	public void send(){
//		sms_log
	}
}
