package com.zz91.sms.service.smslog;

import java.util.Date;

import com.zz91.sms.domain.SmsLog;
import com.zz91.sms.dto.Pager;

public interface SmsLogService {
	
	public final static int SEND_READY=0;
	public final static int SEND_SUCCESS=1;
	public final static int SEND_FAILURE=2;
	public final static int SEND_PROCESS=3;
	
	public Pager<SmsLog> pageLog(Date from,Date to,Integer sendStatus,Pager<SmsLog> page);
	
	public void resend(Integer id);
	
	public Integer remove(Integer id);

}
