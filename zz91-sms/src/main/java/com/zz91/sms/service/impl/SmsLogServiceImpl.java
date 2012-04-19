package com.zz91.sms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zz91.sms.dao.SmsLogDao;
import com.zz91.sms.domain.SmsLogDomain;
import com.zz91.sms.domain.dto.PageDto;
import com.zz91.sms.service.SmsLogService;

@Component("smsLogService")
public class SmsLogServiceImpl implements SmsLogService{

	@Resource
	private SmsLogDao smsLogDao;

	@Override
	public PageDto<SmsLogDomain> pageLog(Date from, Date to, Integer sendStatus,
			PageDto<SmsLogDomain> page) {
		if(page.getSort()==null){
			page.setSort("gmt_send");
		}
		if(page.getDir()==null){
			page.setDir("desc");
		}
			
		page.setRecords(smsLogDao.queryLog(from, to, sendStatus, page));
		page.setTotals(smsLogDao.queryLogCount(from, to, sendStatus));
		return page;
	}

	@Override
	public Integer remove(Integer id) {
		return smsLogDao.delete(id);
	}

	@Override
	public void resend(Integer id) {
		smsLogDao.updateStatus(id, SEND_READY);
	}

}
