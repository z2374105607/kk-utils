package net.kkppyy.utils.file;

import org.junit.Test;

import net.kkppyy.utils.memory.RuntimeRate;

public class ReadbytesUtilTest {
	@Test
	public void readFromByteFileTest(){
		RuntimeRate.getFree();
		byte[] bytes=ReadbytesUtil.readFromByteFile("G:\\filemanage\\test\\10.属性分片and边框分片大模型全专业2.rebim");
		RuntimeRate.getFree();
		System.gc();
		RuntimeRate.getFree();
		System.out.println(bytes);
	}
}
