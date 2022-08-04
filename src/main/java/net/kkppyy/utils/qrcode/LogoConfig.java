package net.kkppyy.utils.qrcode;

import sun.font.FontDesignMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 二维码 添加 logo图标 处理的方法, 模仿微信自动生成二维码效果，有圆角边框，logo和二维码间有空白区，logo带有灰色边框
 * 
 * @author Administrator sangwenhao
 *
 */
public class LogoConfig {

	/**
	 * 设置 logo
	 * 
	 * @param matrixImage
	 *            源二维码图片
	 * @return 返回带有logo的二维码图片
	 * @throws IOException
	 * @author Administrator sangwenhao
	 */
	@SuppressWarnings("restriction")
	public BufferedImage LogoMatrix(BufferedImage matrixImage, String loginpath, String titleimage,String title, String intro)
			throws IOException {
		/**
		 * 读取二维码图片，并构建绘图对象
		 */
		Graphics2D g2 = matrixImage.createGraphics();

		int matrixWidth = matrixImage.getWidth();
		int matrixHeigh = matrixImage.getHeight();

		/**
		 * 读取Logo图片
		 */
		// BufferedImage logo = ImageIO.read(new File("F:\\test.jpg"));
		BufferedImage logo = ImageIO.read(new File(loginpath));

		// 开始绘制图片
		g2.drawImage(logo, matrixWidth / 4, matrixHeigh / 12 * 5, matrixWidth / 2, matrixHeigh / 6, null);// 绘制
		/*
		 * BasicStroke stroke = new
		 * BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		 * g2.setStroke(stroke);// 设置笔画对象 //指定弧度的圆角矩形 RoundRectangle2D.Float
		 * round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2,
		 * matrixWidth/5, matrixHeigh/5,20,20); g2.setColor(Color.white);
		 * g2.draw(round);// 绘制圆弧矩形
		 * 
		 * //设置logo 有一道灰色边框 BasicStroke stroke2 = new
		 * BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		 * g2.setStroke(stroke2);// 设置笔画对象 RoundRectangle2D.Float round2 = new
		 * RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2,
		 * matrixWidth/5-4, matrixHeigh/5-4,20,20); g2.setColor(new
		 * Color(128,128,128)); g2.draw(round2);// 绘制圆弧矩形
		 */
		Font font;
		FontDesignMetrics metrics;
		if(null!=titleimage&&!titleimage.trim().isEmpty()){
		
		font = new Font("微软雅黑", Font.BOLD, 30);
		// String content =
		// "你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯";

		metrics = FontDesignMetrics.getMetrics(font);
		int width = getWordWidth(font, title);//计算字体宽度
		g2.setFont(font);
		g2.setColor(Color.BLUE);
		int y=metrics.getAscent();
		g2.drawString(title, (430-width)/2, y);// 图片上写文字
		
		BufferedImage ftitleimage = ImageIO.read(new File(titleimage));

		// 开始绘制图片
		g2.drawImage(ftitleimage, (430-width)/2-(width/title.length()*2),0, matrixWidth / 8, matrixHeigh / 12, null);// 绘制
		}else{
		font = new Font("微软雅黑", Font.BOLD, 30);
		// String content =
		// "你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯";

		metrics = FontDesignMetrics.getMetrics(font);
		int width = getWordWidth(font, title);//计算字体宽度
		g2.setFont(font);
		g2.setColor(Color.BLUE);
		g2.drawString(title, (430-width)/2, metrics.getAscent());// 图片上写文字
		}
		font = new Font("微软雅黑", Font.PLAIN, 15);
		// content =
		// "你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯你好张凯";
		int length = intro.length();
		int j = 0;
		for (int i = 0; i < length; i += 28) {
			String str;
			if (intro.length() > 28)
				str = intro.substring(0, 28);
			else
				str = intro;
			metrics = FontDesignMetrics.getMetrics(font);
			g2.setFont(font);
			g2.setColor(Color.BLUE);
			g2.drawString(str, 0, metrics.getAscent() + 470 + j * 20);// 图片上写文字
			if (intro.length() < 28) {
				break;
			}
			j++;
			intro = intro.substring(28);
		}
		g2.dispose();
		matrixImage.flush();
		return matrixImage;
	}

	public static int getWordWidth(Font font, String content) {
		FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
		int width = 0;
		for (int i = 0; i < content.length(); i++) {
			width += metrics.charWidth(content.charAt(i));
		}
		return width;
	}

}