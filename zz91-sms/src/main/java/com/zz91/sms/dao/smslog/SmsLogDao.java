package com.zz91.sms.dao.smslog;

import java.util.Date;
import java.util.List;

import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.dto.Pager;

public interface SmsLogDao {

	public Integer delete(Integer id);

	public Integer updateStatus(Integer id, Integer sendStatus);

	public List<SmsLog> queryLog(Date from, Date to, Integer sendStatus,
			String receiver, String gatewayCode, Integer priority,
			String content, Pager<SmsLog> page);

	public Integer queryLogCount(Date from, Date to, String receiver,
			String gatewayCode, Integer priority, String content,
			Integer sendStatus);

	public Integer insert(SmsLog sms);

	public List<SmsLog> querySmsSend(Integer i);

	public Integer recoverStatus(Integer fromStatus, Integer toStatus);

}
