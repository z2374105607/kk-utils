package net.kkppyy.utils.String;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import org.junit.Test;

public class StringTest {
	@Test
	public void stringTest() {
		String a = "abc00000";
		System.out.println(a + "字符串长度:" + a.length() + "--字节码长度:" + a.getBytes().length);
		String b = "张凯好";
		System.out.println(b + "字符串长度:" + b.length() + "--字节码长度:" + b.getBytes().length);
		String c = "a张凯";
		System.out.println(c + "字符串长度:" + c.length() + "--字节码长度:" + c.getBytes().length);
		String d = "张凯a";
		System.out.println(d + "字符串长度:" + d.length() + "--字节码长度:" + d.getBytes().length);
		String e = "123";
		System.out.println(e + "字符串长度:" + e.length() + "--字节码长度:" + e.getBytes().length);
		String f = "张凯1";
		System.out.println(f + "字符串长度:" + f.length() + "--字节码长度:" + f.getBytes().length);
		String g = "a1+";
		System.out.println(g + "字符串长度:" + g.length() + "--字节码长度:" + g.getBytes().length);
		String h = "_@#";
		System.out.println(h + "字符串长度:" + h.length() + "--字节码长度:" + h.getBytes().length);
		String j = "》》》";
		System.out.println(j + "字符串长度:" + j.length() + "--字节码长度:" + j.getBytes().length);

		String text = "张凯好、、？？》》《《";
		String Reg = "^[\u4e00-\u9fa5]{1}$";// 正则
		int result = 0;
		for (int i = 0; i < text.length(); i++) {
			String b1 = Character.toString(text.charAt(i));
			if (b1.matches(Reg))
				result++;
		}
		System.out.println(text + "汉字个数:" + result);
		Font font = new Font("微软雅黑", Font.BOLD, 90);
		int widthF = getWordWidth(font, "张");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		 font = new Font("微软雅黑", Font.BOLD, 90);
		 widthF = getWordWidth(font, "a");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		 font = new Font("微软雅黑", Font.BOLD, 60);
		 widthF = getWordWidth(font, "a");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 60);
		widthF = getWordWidth(font, "张");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 60);
		widthF = getWordWidth(font, "张a");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 45);
		widthF = getWordWidth(font, "a");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 30);
		widthF = getWordWidth(font, "a");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 30);
		widthF = getWordWidth(font, "张");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 30);
		widthF = getWordWidth(font, "张a");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 15);
		widthF = getWordWidth(font, "aA");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 15);
		widthF = getWordWidth(font, "张");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
		font = new Font("微软雅黑", Font.BOLD, 15);
		widthF = getWordWidth(font, "张A");// 计算字体宽度
		System.out.println("宽度:"+widthF+",高度:"+getHeight(font));
	}
	public static int getWordWidth(Font font, String content) {
		FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		int width = 0;
		for (int i = 0; i < content.length(); i++) {
			width += metrics.charWidth(content.charAt(i));
		}
		return width;
	}
	public static int getHeight(Font font) {
		FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		
		return metrics.getHeight();
	}
}
