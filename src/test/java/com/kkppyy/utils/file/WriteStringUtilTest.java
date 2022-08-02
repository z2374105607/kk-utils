package com.kkppyy.utils.file;

import org.junit.Test;

public class WriteStringUtilTest {
	@Test
	public void writeStringUtilTest(){
		String str=ReadStringUtil.readFile("d:/model.gltf");
		WriteStringUtil.writeStringUtil(str, "d:/zh.text");
	}
}
