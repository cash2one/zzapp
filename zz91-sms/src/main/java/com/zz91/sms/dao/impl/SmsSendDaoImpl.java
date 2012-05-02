package com.zz91.sms.dao.impl;

import com.zz91.sms.dao.BaseDao;
import com.zz91.sms.dao.SmsSendDao;

public class SmsSendDaoImpl extends BaseDao implements SmsSendDao{
	
	final static String SQL_PREFIX = "smsLog";

	@Override
	public Integer SendStringSms(String smsDetails) {
		
		return (Integer)getSqlMapClientTemplate().queryForObject(buildId(SQL_PREFIX, "SendStringSms"), smsDetails);
	}

}
