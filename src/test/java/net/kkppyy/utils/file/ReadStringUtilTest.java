package net.kkppyy.utils.file;

import org.junit.Test;

public class ReadStringUtilTest {
	@Test
	public void readFileTest(){
		String str=ReadStringUtil.readFile("d:/json.txt");
		System.out.println(str);
	}
}
