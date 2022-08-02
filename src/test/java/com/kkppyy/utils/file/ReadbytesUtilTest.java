package com.kkppyy.utils.file;

import org.junit.Test;

public class ReadbytesUtilTest {
	@Test
	public void readFromByteFileTest(){
		byte[] bytes=ReadbytesUtil.readFromByteFile("d:/637215002180837981.bin");
		System.out.println(bytes);
	}
}
