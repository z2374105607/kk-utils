package net.kkppyy.utils.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * <p>
 * Title:WriteStringUtil
 * </p>
 * <p>
 * Description: 把字符串写入问价，传入路径和需要写入文件的字符串
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author 张凯
 *
 * @date 2020年4月22日 上午11:14:36
 */
public class WriteStringUtil {
	public static void writeStringUtil(String str, String path) {
		File f = new File(path);// 新建一个文件对象，如果不存在则创建一个该文件
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write(str);// 将字符串写入到指定的路径下的文件中
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写文件方法
	 * 
	 * @param path
	 * @param name
	 * @param content
	 * @param encoding
	 */
	/*public static void writeFile(String path, String name, String content,
			String encoding) {
		if (content == null)
			content = "";
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
                        //利用org.apache.commons.io.FileUtils快速写文件
            org.apache.commons.io.FileUtils.writeStringToFile(new File(path
					+ name), content, encoding);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}*/
}
