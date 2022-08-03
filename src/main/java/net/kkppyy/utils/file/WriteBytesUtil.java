package net.kkppyy.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * <p>Title:WriteBytesUtil</p>
 * <p>Description: 将byte数组写入文件</p>
 * <p>Company: </p>
 *
 * @author 张凯
 *
 * @date 2020年4月22日 下午2:36:28
 */
public class WriteBytesUtil {
	/**
	 * 字节码存成文件
	 * @param bytes 需要存的字节码
	 * @param fileName 文件名称
	 * @param path 存文件的路径
	 */
	public static void writeBytesUtil(byte[] bytes,String fileName,String path){
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(path +"/"+ fileName);
			fos.write(bytes);
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
