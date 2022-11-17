package net.kkppyy.utils.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 
 * <p>Title:ReadbytesUtil</p>
 * <p>Description: 从文件读入二进制数据，传入文件路径</p>
 * <p>Company: </p>
 *
 * @author 张凯
 *
 * @date 2020年4月21日 上午11:18:30
 */
public class ReadbytesUtil {
	public static byte[] readFromByteFile(String pathname) {
		File filename = new File(pathname);
		FileInputStream fileInputStream=null;
		BufferedInputStream in = null;
		byte[] content=null;
		try {
			fileInputStream=new FileInputStream(filename);
			in = new BufferedInputStream(fileInputStream);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			in.close();
			fileInputStream.close();
			content = out.toByteArray();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
	public static byte[] readFromByteFile(InputStream source) {
		BufferedInputStream in = null;
		byte[] content=null;
		try {
			in = new BufferedInputStream(source);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			in.close();
			source.close();
			content = out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
}
