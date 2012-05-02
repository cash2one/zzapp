package com.zz91.sms.dao.smslog.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zz91.sms.dao.smslog.BaseDao;
import com.zz91.sms.dao.smslog.SmsLogDao;
import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.dto.Pager;

@Repository("smsLogDao")
public class SmsLogDaoImpl extends BaseDao implements SmsLogDao {

	final static String SQL_PREFIX = "smsLog";

	@Override
	public Integer delete(Integer id) {
		return (Integer) getSqlMapClientTemplate().delete(
				buildId(SQL_PREFIX, "deleteById"), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsLog> queryLog(String from, String to, Integer sendStatus,
			String receiver, String gatewayCode, Integer priority,
			String content, Pager<SmsLog> page) {
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("from", from);
		list.put("to", to);
		list.put("sendStatus", sendStatus);
		list.put("receiver", receiver);
		list.put("gatewayCode", gatewayCode);
		list.put("priority", priority);
		list.put("content", content);
		list.put("page", page);
		return getSqlMapClientTemplate().queryForList(
				buildId(SQL_PREFIX, "querySmsLog"), list);
	}

	@Override
	public Integer queryLogCount(String from, String to, String receiver, String gatewayCode, Integer priority,
			String content, Integer sendStatus) {
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("from", from);
		list.put("to", to);
		list.put("receiver", receiver);
		list.put("gatewayCode", gatewayCode);
		list.put("priority", priority);
		list.put("content", content);
		list.put("sendStatus", sendStatus);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				buildId(SQL_PREFIX, "querySmsLogCount"), list);
	}

	@Override
	public Integer updateStatus(Integer id, Integer sendStatus) {
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("id", id);
		list.put("sendStatus", sendStatus);
		return (Integer) getSqlMapClientTemplate().update(
				buildId(SQL_PREFIX, "updateSendStatus"), list);
	}

	@Override
	public Integer insert(SmsLog sms) {
		return (Integer) getSqlMapClientTemplate().insert(
				buildId(SQL_PREFIX, "insertSms"), sms);
	}

	@Override
	public List<SmsLog> querySmsSend(Integer i) {
		@SuppressWarnings("unchecked")
		List<SmsLog> list = getSqlMapClientTemplate().queryForList(
				buildId(SQL_PREFIX, "querySmsSend"), i);
		return list;
	}

	@Override
	public Integer recoverStatus(Integer fromStatus, Integer toStatus) {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("fromStatus", fromStatus);
		rootMap.put("toStatus", toStatus);
		return (Integer) getSqlMapClientTemplate().update(
				buildId(SQL_PREFIX, "recoverStatus"), rootMap);
	}

}
