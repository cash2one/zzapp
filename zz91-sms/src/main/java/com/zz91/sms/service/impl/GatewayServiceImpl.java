package com.zz91.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zz91.sms.common.ZZSms;
import com.zz91.sms.dao.GatewayDao;
import com.zz91.sms.domain.Gateway;
import com.zz91.sms.service.GatewayService;
import com.zz91.sms.util.ClassHelper;
import com.zz91.util.Assert;

@Component("gatewayService")
public class GatewayServiceImpl implements GatewayService {
	@Resource
	private GatewayDao gatewayDao;

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
		Assert.notNull(id, "the id can not be null!");
		return gatewayDao.delete(id);
	}

	@Override
	public Integer update(Gateway gateway) {
		return gatewayDao.update(gateway);
	}

	@Override
	public List<Gateway> query(Integer enabled) {
		return gatewayDao.query(enabled);
	}

	@Override
	public Gateway queryOne(Integer id) {
		return gatewayDao.queryOne(id);
	}

	@Override
	public void initGateway() {
		List<Gateway> list = query(ENABLED_TRUE);
		for (Gateway obj : list) {
			
			String key = obj.getCode();
			String value = obj.getApiJar();
			
			ZZSms zzsms = null;
			try {
				zzsms = (ZZSms) ClassHelper.load(value).newInstance();
			} catch (ClassNotFoundException e) {
				zzsms = null;
			} catch (InstantiationException e) {
				zzsms = null;
			} catch (IllegalAccessException e) {
				zzsms = null;
			}
			if (zzsms != null) {
				CACHE_GATEWAY.put(key, zzsms);
			}
		}
	}

	@Override
	public Object balance(String code) {
		ZZSms sms = (ZZSms) GatewayService.CACHE_GATEWAY.get(code);
		return sms.balance();
	}

}
