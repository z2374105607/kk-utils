package net.kkppyy.utils.md5;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Example {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String originalString = "张凯写文件";

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(originalString.getBytes());

        byte[] digest = messageDigest.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b & 0xff));
        }

        System.out.println("Original String: " + originalString);
        System.out.println("MD5 Hash: " + hexString.toString().toUpperCase());
    }
}
