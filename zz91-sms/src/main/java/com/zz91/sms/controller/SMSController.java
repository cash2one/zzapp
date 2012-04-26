package com.zz91.sms.controller;


import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.dto.ExtResult;
import com.zz91.sms.service.smslog.SmsSendService;
import com.zz91.util.datetime.DateUtil;
import com.zz91.util.lang.StringUtils;

@Controller
public class SMSController extends BaseController{
	
	@Resource
	private SmsSendService smsSendService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping
	public ModelAndView send(HttpServletRequest request,Map<String, Object>out,String gmtPostStr,SmsLog sms,String dataMap) throws ParseException{
		Integer i=0;
		if(StringUtils.isNotEmpty(gmtPostStr)){
			try {
				sms.setGmtSend(DateUtil.getDate(gmtPostStr, "yyyy-mm-dd HH:mm:ss"));
			} catch (ParseException e) {
			}
		}else{
			sms.setGmtSend(new Date());
		}
		JSONObject jSONObject = null;
		Map<String,Object> map =new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(dataMap) && dataMap.startsWith("{")){
			jSONObject = JSONObject.fromObject(dataMap);
			Set<String> jbodykey=jSONObject.keySet();
			for(String outkey: jbodykey){
				map.put(outkey, jSONObject.get(outkey));
			}
			sms.setSmsParameter(map);
		}
		
		if(StringUtils.isNotEmpty(sms.getTemplateCode())){
			i = smsSendService.sendSmsByCode(sms);
		}else{
			i=smsSendService.sendSms(sms);
		}
		ExtResult result = new ExtResult();	
		if (i!=null && i > 0) {
			result.setSuccess(true);			
		}
		return printJson(result, out); 
		
	}

}
