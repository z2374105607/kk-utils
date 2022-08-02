package com.kkppyy.utils.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 压缩或解压zip：
 * 由于直接使用java.util.zip工具包下的类，会出现中文乱码问题，所以使用ant.jar中的org.apache.tools.zip下的工具类
 * 
 * @author Administrator
 */
public class ZipUtilByte {
	public static byte[] byteToZip(String entryName, byte[] bytes) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ZipOutputStream zip = new ZipOutputStream(bos);
			ZipEntry entry = new ZipEntry(entryName);
			entry.setSize(bytes.length);
			zip.putNextEntry(entry);
			zip.write(bytes);
			zip.closeEntry();
			zip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	public static byte[] byteToZip(Map<String,byte[]> bytesList) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ZipOutputStream zip = new ZipOutputStream(bos);
			Set<Entry<String, byte[]>>  set=bytesList.entrySet();
			for (Entry<String, byte[]> entryset : set) {
				ZipEntry entry = new ZipEntry(entryset.getKey());
				entry.setSize(entryset.getValue().length);
				zip.putNextEntry(entry);
				zip.write(entryset.getValue());
				zip.closeEntry();
			}
			zip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	/**
	 * 对.zip文件进行解压缩
	 * 
	 * @param zipFile
	 *            解压缩文件
	 * @param descDir
	 *            压缩的目标地址，如：D:\\测试 或 /mnt/d/测试
	 * @param isnewname true 解压文件使用压缩包名称 false 使用本来名称
	 * @return
	 */
	public static Map<String, byte[]> upzipFile(byte[] bytes, boolean isnewname) {
		Map<String, byte[]> result = new HashMap<String, byte[]>();
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ZipInputStream zip = new ZipInputStream(bis);
			while (true) {
				ZipEntry entry = zip.getNextEntry();
				if (null == entry) {
					break;
				}
				String zipFileName = entry.getName().replaceAll("\\\\", "/");
				byte[] buf = new byte[1024];
				int num = -1;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((num = zip.read(buf, 0, buf.length)) != -1) {
					baos.write(buf, 0, num);
				}
				result.put(zipFileName, baos.toByteArray());
				baos.flush();
				baos.close();
			}
			zip.close();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
