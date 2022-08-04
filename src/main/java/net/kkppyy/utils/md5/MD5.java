package net.kkppyy.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class MD5 {
	public static String getmd5(byte[] bytes) {
		MessageDigest md;
		String myChecksum = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte[] digest = md.digest();
			myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myChecksum;
	}
}
