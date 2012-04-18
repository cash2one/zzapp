package com.zz91.sms.service.gateway.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zz91.sms.dao.gateway.GatewayDao;
import com.zz91.sms.domain.Gateway;
import com.zz91.sms.service.gateway.GatewayService;

@Component("gatewayService")
public class GatewayServiceImpl implements GatewayService {
	
	@Resource
	private GatewayDao gatewayDao;

	@Override
	public Object balance(Integer id) {
		return null;
	}

	@Override
	public Integer create(Gateway gateway) {
		return gatewayDao.insert(gateway);
	}

	@Override
	public void disabled(Integer id) {
		gatewayDao.updateEnabled(id, ENABLED_FALSE);
	}

	@Override
	public void enabled(Integer id) {
		gatewayDao.updateEnabled(id, ENABLED_TRUE);
	}

	@Override
	public boolean gatwayTest(String to, Integer id) {
		return false;
	}

	@Override
	public Integer remove(Integer id) {
		Integer i = gatewayDao.delete(id);
		if(i!=null && i.intValue()>0) {
			return i;
		}
		return 0;
	}

	@Override
	public Integer update(Gateway gateway) {
		return gatewayDao.update(gateway);
	}

	@Override
	public List<Gateway> query(Integer enabled) {
		return gatewayDao.query(enabled);
	}

}
