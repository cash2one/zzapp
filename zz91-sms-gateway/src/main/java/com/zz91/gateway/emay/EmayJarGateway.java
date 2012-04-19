package com.zz91.gateway.emay;

import org.apache.log4j.Logger;

import cn.emay.sdk.client.api.Client;

import com.zz91.sms.common.ZZSms;

public class EmayJarGateway implements ZZSms {
	private static Logger LOG = Logger.getLogger(EmayJarGateway.class);
	private final static String SERIAL_NO = "6SDK-EMY-6688-JCTPN";
	private final static String SERIAL_PAS = "909416";
	private final static int SMS_PRIORITY = 3;
	private Client client = null;

	public EmayJarGateway() {
		init();
	}

	@Override
	public Integer send(String mobile, String content) {
		String[] mobiles = new String[] { mobile };
		return client.sendSMS(mobiles, content, SMS_PRIORITY);
	}

	@Override
	public Object balance() {
		try {
			return client.getBalance();
		} catch (Exception e) {
			return 0;
		}
	}

	private void init() {
		if (client == null) {
			try {
				client = new Client(SERIAL_NO, SERIAL_PAS);
			} catch (Exception e) {
				LOG.error("SERIAL_NO or SERIAL_PAS error");
			}
		}
	}
}
