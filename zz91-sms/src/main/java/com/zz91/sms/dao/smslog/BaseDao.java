package com.zz91.sms.dao.smslog;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class BaseDao extends SqlMapClientDaoSupport {

	public String buildId(String prefix, String sqlId){
		return prefix+"."+sqlId;
	}
}

