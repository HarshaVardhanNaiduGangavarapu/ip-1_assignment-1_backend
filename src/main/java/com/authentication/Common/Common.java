package com.authentication.Common;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

	private static final String key = "Gh2212$-uT1056";
	
	public static String getCurrentDate(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date()) + "";
	}
	
	public static String encryptStringAdvance(String plainText) {
		String strCipherText = "";
		try {
			plainText = plainText + "h@y^Iu$trN";
			byte[] defaultBytes = plainText.getBytes();
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte[] messageDigest = algorithm.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			strCipherText = hexString.substring(0);
		} catch (Exception ex) {

		}
		return strCipherText;
	}
}
