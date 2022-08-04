package net.kkppyy.utils.number;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Assert;
import org.junit.Test;

public class FloatToBytesTest {
	@Test
	public void getBytestest() throws IOException {
		float v = 1f;
		Byte[] value = FloatToBytes.getBytes(1f);
		ByteBuffer buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		buf = buf.order(ByteOrder.LITTLE_ENDIAN);// 默认大端，小端用这行
		byte[] valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = value[i].byteValue();
		}
		buf.put(valurbate);
		buf.rewind();
		float f1 = buf.getFloat();
		System.out.println(f1);
		Assert.assertTrue(f1== v);

		float v1 = 0.1f;
		Byte[] value1 = FloatToBytes.getBytes(0.1f);
		buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		buf = buf.order(ByteOrder.LITTLE_ENDIAN);// 默认大端，小端用这行
		valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = value1[i].byteValue();
		}
		buf.put(valurbate);
		buf.rewind();
		float f2 = buf.getFloat();
		Assert.assertTrue(f2== v1);
		System.out.println(f2);

		Byte[] value2 = FloatToBytes.getBytes(-0.1f);
		buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		buf = buf.order(ByteOrder.LITTLE_ENDIAN);// 默认大端，小端用这行
		valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = value2[i].byteValue();
		}
		buf.put(valurbate);
		buf.rewind();
		float f3 = buf.getFloat();
		System.out.println(f3);

		Byte[] value3 = FloatToBytes.getBytes(5.1f);
		buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		buf = buf.order(ByteOrder.LITTLE_ENDIAN);// 默认大端，小端用这行
		valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = value3[i].byteValue();
		}
		buf.put(valurbate);
		buf.rewind();
		float f4 = buf.getFloat();
		System.out.println(f4);

		Byte[] value4 = FloatToBytes.getBytes(-5.1f);
		buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		buf = buf.order(ByteOrder.LITTLE_ENDIAN);// 默认大端，小端用这行
		valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = value4[i].byteValue();
		}
		buf.put(valurbate);
		buf.rewind();
		float f5 = buf.getFloat();
		System.out.println(f5);

		Byte[] value5 = FloatToBytes.getBytes(20.3f);
		buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		buf = buf.order(ByteOrder.LITTLE_ENDIAN);// 默认大端，小端用这行
		valurbate = new byte[4];
		for (int i = 0; i < 4; i++) {
			valurbate[i] = value5[i].byteValue();
		}
		buf.put(valurbate);
		buf.rewind();
		float f6 = buf.getFloat();
		System.out.println(f6);

		System.out.println(value);
	}

	@Test
	public void getBytesByInttest() throws IOException {
		float v = 1f;
		Byte[] value = FloatToBytes.getBytesByInt(1f);

		float f1 = FloatToBytes.getFloatLittleEndian(value);
		Assert.assertTrue(f1== v);
		System.out.println(f1);

		Byte[] value1 = FloatToBytes.getBytesByInt(0.1f);

		float f2 = FloatToBytes.getFloatLittleEndian(value1);
		Assert.assertTrue(f1== v);
		System.out.println(f2);

		Byte[] value2 = FloatToBytes.getBytesByInt(-0.1f);

		float f3 = FloatToBytes.getFloatLittleEndian(value2);
		System.out.println(f3);

		Byte[] value3 = FloatToBytes.getBytesByInt(5.1f);

		float f4 = FloatToBytes.getFloatLittleEndian(value3);
		System.out.println(f4);

		Byte[] value4 = FloatToBytes.getBytesByInt(-5.1f);

		float f5 = FloatToBytes.getFloatLittleEndian(value4);
		System.out.println(f5);

		Byte[] value5 = FloatToBytes.getBytesByInt(20.3f);

		float f6 = FloatToBytes.getFloatLittleEndian(value5);
		System.out.println(f6);
		System.out.println(value);
	}
}
