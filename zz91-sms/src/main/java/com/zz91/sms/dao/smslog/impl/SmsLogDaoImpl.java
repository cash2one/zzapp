package com.zz91.sms.dao.smslog.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zz91.sms.dao.smslog.BaseDao;
import com.zz91.sms.dao.smslog.SmsLogDao;
import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.dto.Pager;

@Component("smsLogDao")
public class SmsLogDaoImpl extends BaseDao implements SmsLogDao{

	final static String SQL_PREFIX = "smsLog";
		
	@Override
	public Integer delete(Integer id) {
		return (Integer) getSqlMapClientTemplate().delete(buildId(SQL_PREFIX, "deleteById"), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsLog> queryLog(Date from, Date to, Integer sendStatus,
			Pager<SmsLog> page) {
		Map<String,Object> list=new HashMap<String, Object>();
		list.put("from", from);
		list.put("to", to);
		list.put("sendStatus", sendStatus);
		list.put("page", page);
		return getSqlMapClientTemplate().queryForList(buildId(SQL_PREFIX,"querySmsLog"),list);
	}

	@Override
	public Integer queryLogCount(Date from, Date to, Integer sendStatus) {
		Map<String, Object> list=new HashMap<String, Object>();
		list.put("from", from);
		list.put("to", to);
		list.put("sendStatus", sendStatus);
		return (Integer) getSqlMapClientTemplate().queryForObject(buildId(SQL_PREFIX,"querySmsLogCount"),list);
	}

	@Override
	public Integer updateStatus(Integer id, Integer sendStatus) {
		Map<String, Object>list=new HashMap<String, Object>();
		list.put("id", id);
		list.put("sendStatus", sendStatus);
		return (Integer) getSqlMapClientTemplate().update(buildId(SQL_PREFIX,"updateSendStatus"),list);
	}

}
