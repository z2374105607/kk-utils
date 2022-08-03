package net.kkppyy.utils.file;

import java.io.File;

/**
 * <p>Title:FileDelete</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author 张凯
 *
 * @date 2020年8月4日 下午2:02:01
 */

public class FileDelete {
	public static void deleteFile(File file) {
		if (file.isFile()) {// 判断是否为文件，是，则删除
			// System.out.println(file.getAbsoluteFile());// 打印路径
			String filename = file.getName();
			if (filename.contains(".bin") || filename.contains("model.zip") || filename.contains("new-1.gif")
					|| filename.contains("preview.jpg") || filename.contains(".rebim") || filename.contains(".json"))
				if (file.delete()) {
					System.out.println(file.getAbsoluteFile() + "---删除成功");
				} else {
					System.out.println(file.getAbsoluteFile() + "---删除失败");
				}
		} else {// 不为文件，则为文件夹
			String[] childFilePath = file.list();// 获取文件夹下所有文件相对路径
			if (null != childFilePath)
				for (String path : childFilePath) {
					File childFile = new File(file.getAbsoluteFile() + "/" + path);
					deleteFile(childFile);// 递归，对每个都进行判断
				}
			if (null != file)
			if (file.delete()) {
				System.out.println(file.getAbsoluteFile() + "---删除成功");
			} else {
				System.out.println(file.getAbsoluteFile() + "---删除失败");
				return;
			}
		}
	}


}
