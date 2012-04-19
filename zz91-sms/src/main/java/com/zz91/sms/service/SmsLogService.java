package com.zz91.sms.service;

import java.util.Date;

import com.zz91.sms.domain.SmsLogDomain;
import com.zz91.sms.domain.dto.PageDto;

public interface SmsLogService {
	
	public final static int SEND_READY=0;
	public final static int SEND_SUCCESS=1;
	public final static int SEND_FAILURE=2;
	public final static int SEND_PROCESS=3;
	
	public PageDto<SmsLogDomain> pageLog(Date from,Date to,Integer sendStatus,PageDto<SmsLogDomain> page);
	
	public void resend(Integer id);
	
	public Integer remove(Integer id);

}
