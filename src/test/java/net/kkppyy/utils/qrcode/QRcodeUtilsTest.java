package net.kkppyy.utils.qrcode;

import java.io.IOException;

import org.junit.Test;

import com.google.zxing.WriterException;

import net.kkppyy.utils.file.ReadbytesUtil;
import net.kkppyy.utils.file.WriteBytesUtil;

public class QRcodeUtilsTest {
	@Test
	public void getQRcodeBytesTest() throws WriterException, IOException{
		byte[] imageLogo =ReadbytesUtil.readFromByteFile("G:/logo.jpg");
		byte[] bytes=QRcodeUtils.getQRcodeBytes("张凯kaikai123", "一二三四五六七八九十壹贰叁肆伍陆柒捌玖拾一二三四五六七八九十壹贰叁肆伍陆柒捌玖拾", "一二三四五六七八九十壹贰叁肆伍陆柒捌玖拾一二三四五六七八九十壹贰叁肆伍陆柒捌玖拾一二三四五六七八九十壹贰叁肆伍陆柒捌玖拾一二三四五六七八九十壹贰叁肆伍陆柒捌玖拾", imageLogo);
		System.out.println("张凯AAAAAAAAAAA");
		System.out.println("一二三四五六七八九十壹贰叁肆伍陆柒捌玖拾");
		WriteBytesUtil.writeBytesUtil(bytes, "kk11.jpg", "g:/");
	}
}
