package net.kkppyy.utils.bytes;

public class ByteSplit {
	public static byte[] byteSplit(int off, int len, byte[] bytes) {
		byte[] subbyte = new byte[len];
		for (int i = off, j = 0; i < len + off; i++, j++) {
			subbyte[j] = bytes[i];
		}
		return subbyte;
	}
}
