/**
 * 
 */
package com.zz91.sms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zz91.sms.domain.Gateway;
import com.zz91.sms.dto.ExtResult;
import com.zz91.sms.service.gateway.GatewayService;

/**
 * @author root
 * 
 */
@Controller
public class GatewayController extends BaseController {
	
	@Resource
	private GatewayService gateService;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out){
		return null;
	}
	
	@RequestMapping
	public ModelAndView query(HttpServletRequest request,Map<String, Object>out,Integer enabled){
		
		List<Gateway> list=gateService.query(enabled);
		
		return printJson(list, out);
	}
	
	@RequestMapping
	public ModelAndView delete(HttpServletRequest requst,Map<String, Object>out,Integer id){
		
		Integer i=gateService.remove(id);
		
		ExtResult result=new ExtResult();
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
		}
		return printJson(result, out);
	}
	
	@RequestMapping
	public ModelAndView add(HttpServletRequest request,Map<String, Object>out ,Gateway gateway){
		
		Integer i=gateService.create(gateway);
		
		ExtResult result=new ExtResult();
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
		}
		return printJson(result, out);
		
	}
	
	@RequestMapping
	public ModelAndView queryOne(HttpServletRequest request,Map<String, Object>out,Integer id){
		
		Gateway gateway=gateService.queryOne(id);
		
		ExtResult result=new ExtResult();
		if(gateway!=null){
			result.setSuccess(true);
		}
		return printJson(result, out);
	}
	
	@RequestMapping
	public ModelAndView update(HttpServletRequest request,Map<String, Object>out,Gateway gateway){
		
		Integer i=gateService.update(gateway);
		
		ExtResult result=new ExtResult();
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
		}
		return printJson(result, out);
	}

}
