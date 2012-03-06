/**
 * Project name: zz91-mail
 * File name: MailInfoDaoImpl.java
 * Copyright: 2005-2011 ASTO Info TechCo.,Ltd. All rights reserved
 */
package com.zz91.mail.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zz91.mail.dao.BaseDao;
import com.zz91.mail.dao.MailInfoDao;
import com.zz91.mail.domain.MailInfoDomain;

/**
 * @author kongsj
 * @email kongsj@zz91.net
 * @date 2011-11-10
 */
@Repository("mailInfoDao")
public class MailInfoDaoImpl extends BaseDao implements MailInfoDao {

    final static String SQL_PREFIX = "mailInfo";

    @Override
    public MailInfoDomain selectById(Integer id) {
        return (MailInfoDomain) getSqlMapClientTemplate().queryForObject(buildId(SQL_PREFIX, "selectById"), id);
    }

    @Override
    public Integer insert(MailInfoDomain mto) {
        return (Integer) getSqlMapClientTemplate().insert(buildId(SQL_PREFIX, "insert"), mto);
    }

    @Override
    public Integer update(MailInfoDomain mto) {
        return (Integer) getSqlMapClientTemplate().update(buildId(SQL_PREFIX, "update"), mto);
    }

    @Override
    public Integer deleteById(Integer id) {
        return (Integer) getSqlMapClientTemplate().delete(buildId(SQL_PREFIX, "deleteById"), id);
    }

    @Override
    public List<MailInfoDomain> selectMailQueue(Map<String, Object> map) {
        @SuppressWarnings("unchecked")
        List<MailInfoDomain> list = getSqlMapClientTemplate().queryForList(buildId(SQL_PREFIX, "selectMailQueue"), map);
        return list;
    }

    @Override
    public List<MailInfoDomain> queryMailForSend(Integer i) {
        @SuppressWarnings("unchecked")
        List<MailInfoDomain> list = getSqlMapClientTemplate().queryForList(buildId(SQL_PREFIX, "queryMailForSend"), i);
        return list;
    }

	@Override
	public Integer updateSendStatus(Integer id, Integer status) {
		Map<String, Object> root=new HashMap<String, Object>();
		root.put("id", id);
		root.put("sendStatus", status);
		return getSqlMapClientTemplate().update(buildId(SQL_PREFIX, "updateSendStatus"), root);
	}

	@Override
	public Integer recoverStatus(Integer fromStatus, Integer toStatus) {
		Map<String, Object> root=new HashMap<String, Object>();
		root.put("fromStatus", fromStatus);
		root.put("toStatus", toStatus);
		return getSqlMapClientTemplate().update(buildId(SQL_PREFIX, "recoverStatus"), root);
	}

//    @Override
//    public Integer updateSending(Integer id) {
//        return (Integer) getSqlMapClientTemplate().update(buildId(SQL_PREFIX, "updateSending"), id);
//    }
//
//    @Override
//    public Integer updateComplete(Map<String, Integer> map) {
//        return (Integer) getSqlMapClientTemplate().update(buildId(SQL_PREFIX, "updateComplete"), map);
//    }

}
