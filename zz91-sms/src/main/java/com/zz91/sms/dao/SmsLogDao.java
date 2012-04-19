package com.zz91.sms.dao;

import java.util.Date;
import java.util.List;


import com.zz91.sms.domain.SmsLogDomain;
import com.zz91.sms.domain.dto.PageDto;

public interface SmsLogDao {
	
	public Integer delete(Integer id);
	
	public Integer updateStatus(Integer id,Integer sendStatus);
	
	public List<SmsLogDomain> queryLog(Date from,Date to,Integer sendStatus,PageDto<SmsLogDomain> page);
	
	public Integer queryLogCount(Date from,Date to,Integer sendStatus);

}
