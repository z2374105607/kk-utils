package net.kkppyy.utils.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static byte[] getImageBytes(String content) {
		int width = 512; // 二维码图片宽度 300
		int height = 430; // 二维码图片高度300

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();

		g2.setColor(Color.white);
		g2.fillRect(0, 0, width, height);
		
		Font font = new Font("微软雅黑", Font.BOLD, 100);
		FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		;
		int widthF = getWordWidth(font, content);// 计算字体宽度
		g2.setFont(font);
		g2.setColor(Color.black);
		int y = metrics.getAscent();
		g2.drawString(content, (430 - widthF) / 2, (height)/2);// 图片上写文字
		g2.dispose();
		bufferedImage.flush();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bufferedImage, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();
		return bytes;
	}

	public static int getWordWidth(Font font, String content) {
		FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		int width = 0;
		for (int i = 0; i < content.length(); i++) {
			width += metrics.charWidth(content.charAt(i));
		}
		return width;
	}
}
