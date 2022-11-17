package net.kkppyy.utils.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * <p>Title:ReadStringUtil</p>
 * <p>Description: 从文件读入字符串，传入文件路径</p>
 * <p>Company: </p>
 *
 * @author 张凯
 *
 * @date 2020年4月21日 下午1:58:09
 */
public class ReadStringUtil {
	public static String readFile(String path) {
		BufferedReader reader = null;
		InputStreamReader inputStreamReader =null;
		FileInputStream fileInputStream =null;
		String laststr = "";
		try {
			fileInputStream = new FileInputStream(path);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
			fileInputStream.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
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
		return laststr;
	}
}
