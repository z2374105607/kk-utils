package net.kkppyy.utils.number;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class FloatToBytes {
	public static Byte[] getBytesByInt(float data) {
		int intBits = Float.floatToIntBits(data);
		return getBytes(intBits);
	}

	public static Byte[] getBytes(int data) {
		Byte[] bytes = new Byte[4];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		bytes[2] = (byte) ((data & 0xff0000) >> 16);
		bytes[3] = (byte) ((data & 0xff000000) >> 24);
		return bytes;
	}
	public static byte[] getbytesByInt(float data) {
		int intBits = Float.floatToIntBits(data);
		return getbytes(intBits);
	}
	
	public static byte[] getbytes(int data) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		bytes[2] = (byte) ((data & 0xff0000) >> 16);
		bytes[3] = (byte) ((data & 0xff000000) >> 24);
		return bytes;
	}

	public static Byte[] getBytes(float data) {
		ByteBuffer bbuf = ByteBuffer.allocate(4);
		bbuf.putFloat(data);
		byte[] bBuffer = bbuf.array();
		// 数值反传
		ArrayList<Byte> al = new ArrayList<Byte>();
		for (int i = bBuffer.length - 1; i >= 0; i--) {
			al.add(bBuffer[i]);
		}

		Byte[] buffer = new Byte[al.size()];
		for (int i = 0; i <= buffer.length - 1; i++) {
			buffer[i] = al.get(i);
		}
		return buffer;
	}

	public static float getFloatBigEndian(Byte[] data) {
		byte[] valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = data[i].byteValue();
		}
		ByteBuffer buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		// buf=buf.order(ByteOrder.LITTLE_ENDIAN);//默认大端，小端用这行
		buf.put(valurbate);
		buf.rewind();
		return buf.getFloat();
	}

	public static float getFloatLittleEndian(Byte[] data) {
		byte[] valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = data[i].byteValue();
		}
		ByteBuffer buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		 buf=buf.order(ByteOrder.LITTLE_ENDIAN);//默认大端，小端用这行
		buf.put(valurbate);
		buf.rewind();
		return buf.getFloat();
	}

}
