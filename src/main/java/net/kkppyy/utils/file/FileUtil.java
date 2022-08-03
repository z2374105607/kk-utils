package net.kkppyy.utils.file;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: FileSearchUtil
 * @Description： 文件搜索工具类
 * @author: 高举
 * @Date: 2020/5/22 11:09
 * @Version: 1.0
 */
public class FileUtil {

    /**
     * 按照后缀名查找
     * @param folder 文件路径
     * @param keyword 后缀名
     * @return
     */
    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<File>();
        if (folder.isFile()) {
            result.add(folder);
        }

        /*查找后缀名*/
        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().toLowerCase().endsWith(keyword)) {
                    return true;
                }
                return false;
            }
        });

        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
    }
    /**
     * 按照后缀名查找(仅限当前文件夹，不包含子文件夹)
     * @param folder 文件路径
     * @param keyword 后缀名
     * @return
     */
    public static List<File> searchFilesC(File folder, final String keyword) {
    	List<File> result = new ArrayList<File>();
    	if (folder.isFile()) {
    		result.add(folder);
    	}
    	
    	/*查找后缀名*/
    	File[] subFolders = folder.listFiles(new FileFilter() {
    		@Override
    		public boolean accept(File file) {
    			if (file.isDirectory()) {
    				return true;
    			}
    			if (file.getName().toLowerCase().endsWith(keyword)) {
    				return true;
    			}
    			return false;
    		}
    	});
    	
    	if (subFolders != null) {
    		for (File file : subFolders) {
    			if (file.isFile()) {
    				// 如果是文件则将文件添加到结果列表中
    				result.add(file);
    			} 
    		}
    	}
    	return result;
    }
}
