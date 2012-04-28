package com.zz91.gateway.emay;

import cn.emay.sdk.client.api.Client;

import com.zz91.sms.common.ZZSms;

public class EmayJarGateway implements ZZSms {
	private final static String SERIAL_NO = "6SDK-EMY-6688-JCTPN";
	private final static String SERIAL_PAS = "909416";
	private final static int SMS_PRIORITY = 3;
	private static Client client = null;

	@Override
	public Integer send(String mobile, String content) {
		if(client==null){
			init();
		}
		String[] mobiles = new String[] { mobile };
		Integer i = client.sendSMS(mobiles, content, SMS_PRIORITY);
		if(i==0){
			return 2;
		}
		return i;
	}

	@Override
	public Object balance() {
		if(client==null){
			init();
		}
		try {
			return client.getBalance().replace(".", "");
		} catch (Exception e) {
			return 0;
		}
	}

	private synchronized static void init() {
		if (client == null) {
			try {
				client = new Client(SERIAL_NO, SERIAL_PAS);
			} catch (Exception e) {
			}
		}
	}
	public static void main(String[] args){
		EmayJarGateway gateway = new EmayJarGateway();
		gateway.send("13738194812", "dd【zz91】");
	}
}
