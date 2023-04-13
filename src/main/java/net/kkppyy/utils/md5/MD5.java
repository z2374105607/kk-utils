package net.kkppyy.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

public class MD5 {
	public static String getmd5(byte[] bytes) {
		/*
		 * MessageDigest md; String myChecksum = ""; try { md =
		 * MessageDigest.getInstance("MD5"); md.update(bytes); byte[] digest =
		 * md.digest(); myChecksum =
		 * DatatypeConverter.printHexBinary(digest).toUpperCase(); } catch
		 * (NoSuchAlgorithmException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return myChecksum;
		 */
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);

			byte[] digest = messageDigest.digest();

			StringBuilder hexString = new StringBuilder();
			for (byte b : digest) {
				hexString.append(String.format("%02x", b & 0xff));
			}
			System.out.println("MD5 Hash: " + hexString.toString());
			return hexString.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
