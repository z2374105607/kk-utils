package net.kkppyy.utils.qrcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRcodeUtils {
	private static final int BLACK = 0xFF000000;// 用于设置图案的颜色
	private static final int WHITE = 0xFFFFFFFF; // 用于背景色

	public static byte[] getQRcodeBytes(String contents, String title, String desc, byte[] imageLogo)
			throws WriterException, IOException {

		int width = 430; // 二维码图片宽度 300
		int height = 430; // 二维码图片高度300

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 内容所使用字符集编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, 1);// 设置二维码边的空度，非负数

		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, // 要编码的内容
				// 编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
				// Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
				// MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E
				// 1D,UPC/EAN extension,UPC_EAN_EXTENSION
				BarcodeFormat.QR_CODE, 
				width, // 条形码的宽度
				height, // 条形码的高度
				hints);// 生成条形码时的一些配置,此项可选
		BufferedImage image = toBufferedImage(bitMatrix);
		image = LogoMatrix(image,imageLogo,title,desc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();
		return bytes;
	}

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
	public static BufferedImage LogoMatrix(BufferedImage matrixImage, byte[] logoImage,String title, String desc)
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
		InputStream input = new ByteArrayInputStream(logoImage);
		BufferedImage logo = ImageIO.read(input);

		// 开始绘制图片
		g2.drawImage(logo, matrixWidth / 4, matrixHeigh / 12 * 5, matrixWidth / 2, matrixHeigh / 6, null);// 绘制
	
		Font font;
		FontMetrics metrics;
		
		font = new Font("微软雅黑", Font.BOLD, 30);
		int width = getWordWidth(font, title);//计算字体宽度
		List<String> strList=new ArrayList<String>();
		if(width>430)
		{
			font = new Font("微软雅黑", Font.BOLD, 25);
			width = getWordWidth(font, title);//计算字体宽度
		} if(width>430) {
			font = new Font("微软雅黑", Font.BOLD, 20);
			width = getWordWidth(font, title);//计算字体宽度
		}
		 if(width>430) {
			font = new Font("微软雅黑", Font.BOLD, 15);
			width = getWordWidth(font, title);//计算字体宽度
		} if(width>430) {
			metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
			width = 0;
			StringBuffer str=new StringBuffer();
			for (int i = 0; i < title.length(); i++) {
				width += metrics.charWidth(title.charAt(i));
				if(width>400) {
					strList.add(str.toString());
					width=0;
					str=new StringBuffer();
				}
				str.append(title.charAt(i));
			}
			if(str.length()>0)
			strList.add(str.toString());
		}
		if(strList.size()==0) {
			strList.add(title);
		}
		drowString(strList, g2, font);
		
		font = new Font("微软雅黑", Font.PLAIN, 15);
		

		metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		width = 0;
		StringBuffer str=new StringBuffer();
		strList=new ArrayList<String>();
		for (int i = 0; i < desc.length(); i++) {
			width += metrics.charWidth(desc.charAt(i));
			if(width>420) {
				strList.add(str.toString());
				width=0;
				str=new StringBuffer();
			}
			str.append(desc.charAt(i));
		}
		if(str.length()>0)
		strList.add(str.toString());
	
		
		int length = strList.size();
		for (int i = 0; i < length; i ++) {
			metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
			g2.setFont(font);
			g2.setColor(Color.BLUE);
			g2.drawString(strList.get(i), 0, metrics.getAscent() + 470 + i * 20);// 图片上写文字
		}
		g2.dispose();
		matrixImage.flush();
		return matrixImage;
	}

	private static void drowString(List<String> title, Graphics2D g2, Font font) {
		int count=title.size();
		int height=60/count;
		if(height<15) {
			height=15;
		}
		for (int i=0;i<count;i++) {
			FontMetrics metrics= Toolkit.getDefaultToolkit().getFontMetrics(font);;
			int heightFont=getHeight(font);
			g2.setFont(font);
			g2.setColor(Color.BLUE);
			g2.drawString(title.get(i), (430-getWordWidth(font,  title.get(i)))/2, (height-heightFont)/2+(height*i)+20);// 图片上写文字
		}
	}
	
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height + 100, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < 40; y++) {
				image.setRGB(x, y, WHITE);
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y + 40, (matrix.get(x, y) ? BLACK : WHITE));
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = height + 40; y < height + 40 + 60; y++) {
				image.setRGB(x, y, WHITE);
			}
		}

		return image;
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
