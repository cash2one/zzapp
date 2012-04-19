package com.zz91.sms.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import com.zz91.sms.service.gateway.GatewayService;
import com.zz91.sms.test.BaseTestCase;

public class GatewayServiceImplTest extends BaseTestCase{
	
	@Resource
	private GatewayService gatewayService;
	
	/*
	 * 测试添加
	 */
	
	public void test_insert_gateway() {
		
	}
	
	/*
	 * 测试删除
	 */
	
	public void test_delete_gateway() {
		clean();
		int id = insert("删除测试",null);
		
		Integer rm = gatewayService.remove(id);
		assertNotNull(rm);
		assertEquals(1, rm.intValue());
	}
	
	/*
	 * 测试更新
	 */
	
	public void test_update_gateway() {
		
	}
	
	public void test_update_enabled() {
		
	}
	
	public void test_update_disabled() {
		
	}
	
	/*
	 * 测试查询
	 */
	
	
	public int insert(String code, Integer enabled) {
		if(code == null) {
			code = "test";
		}
		if(enabled == null) {
			enabled = 0;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO gateway ");
		sb.append("(code,titles,enabled,serial_no,serial_pas,api_jar,docs,gmt_created,gmt_modified) ");
		sb.append("VALUES ");
		sb.append("('"+ code +"','titles',").append(enabled).append(",'serial_no','serial_pas','api_jar','docs',now(),now())");
		try {
			connection.prepareStatement(sb.toString()).execute();
			return getLastInsertId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void clean() {
		try {
			connection.prepareStatement("DELETE FROM gateway").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
