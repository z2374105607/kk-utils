package net.kkppyy.utils.file;

import org.junit.Test;

public class WriteBytesUtilTest {
	@Test
	public void writeBytesUtilTest(){
		byte[] bytes=ReadbytesUtil.readFromByteFile("d:/637214970136155402.bin");
		WriteBytesUtil.writeBytesUtil(bytes, "kk.bin", "d:/");
	}
}
