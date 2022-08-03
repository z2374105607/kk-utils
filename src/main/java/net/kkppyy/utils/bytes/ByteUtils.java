package net.kkppyy.utils.bytes;

import java.util.List;

public class ByteUtils {
	/**
	 *  @Description sysCopy 合并多个byte[]内容
	 *  @param srcArrays 
	 *  @return byte[] 返回类型 
	 *  @throws
	 */
	public static byte[] sysCopy(List<byte[]> srcArrays) {
		int len = 0;
		for (byte[] srcArray : srcArrays) {
			len += srcArray.length;
		}
		byte[] destArray = new byte[len];
		int destLen = 0;
		for (byte[] srcArray : srcArrays) {
			System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
			destLen += srcArray.length;
		}
		return destArray;
	}

	/**
	 * @Title: bytes2HexString 
	 * @Description: byte[]转换16进制
	 * @param
	 * @return @return String 返回类型 
	 * @throws
	 */
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/**
	 * @Title: byte2HexString 
	 * @Description: byte转换16进制
	 *  @param b @return 
	 *  @return
	 * String 返回类型
	 *  @throws
	 */
	public static String byte2HexString(byte b) {
		String ret = "";
		String hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		ret += hex.toUpperCase();
		return ret;
	}

	/**
	 * @Title: getReverse 
	 * @Description: byte[]倒序排列 
	 * @param t @return 
	 * @return
	 * byte[] 返回类型 @throws
	 */
	public static byte[] getReverse(byte[] t) {
		for (int start = 0, end = t.length - 1; start < end; start++, end--) {
			byte temp = t[end];
			t[end] = t[start];
			t[start] = temp;
		}
		return t;
	}

	/**
	 * @Title: intToByte4 
	 * @Description: int整数转换为4字节的byte数组
	 *  @param
	 * i 
	 * @return @return byte[] 返回类型 
	 * @throws
	 */
	public static byte[] intToByte4(int i) {
		byte[] targets = new byte[4];
		targets[3] = (byte) (i & 0xFF);
		targets[2] = (byte) (i >> 8 & 0xFF);
		targets[1] = (byte) (i >> 16 & 0xFF);
		targets[0] = (byte) (i >> 24 & 0xFF);
		return targets;
	}

}
