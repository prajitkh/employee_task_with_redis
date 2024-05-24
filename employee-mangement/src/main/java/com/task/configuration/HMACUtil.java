package com.task.configuration;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACUtil {

	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SECRET_KEY = "sdkfkfhkdshfsdfhsdfsf1221$#43322";

	public static String generateHMAC(String data) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_ALGO);
		Mac mac = Mac.getInstance(HMAC_ALGO);
		mac.init(secretKeySpec);
		byte[] hmacBytes = mac.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(hmacBytes);
	}

	public static boolean verifyHMAC(String data, String receivedHmac) throws Exception {
		String calculatedHmac = generateHMAC(data);
		return calculatedHmac.equals(receivedHmac);
	}
}
