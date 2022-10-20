package net.kkppyy.utils.image;

import org.junit.Test;

import net.kkppyy.utils.file.WriteBytesUtil;

public class ImageUtilsTest {
	@Test
	public void getImageBytesTest(){
		byte[] imageBytes=ImageUtils.getImageBytes("ifc模型");
		WriteBytesUtil.writeBytesUtil(imageBytes, "凯凯图片7.jpg", "K:\\image\\javaCreateImage");
	}
}
