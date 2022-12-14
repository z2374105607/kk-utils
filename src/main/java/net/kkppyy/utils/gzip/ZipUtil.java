package net.kkppyy.utils.gzip;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * 压缩或解压zip：
 * 由于直接使用java.util.zip工具包下的类，会出现中文乱码问题，所以使用ant.jar中的org.apache.tools.zip下的工具类
 * 
 * @author Administrator
 */

public class ZipUtil {
	public static void byteToZip(String zip, String entryName, byte[] bytes) {
		ZipOutputStream _zipOut;
		try {
			_zipOut = new ZipOutputStream(new FileOutputStream(new File(zip)));
			_zipOut.putNextEntry(new ZipEntry(entryName));
			for (int i = 0; i < bytes.length; i += 1000) {
				byte[] bytetemp;
				if (bytes.length - i > 1000) {
					bytetemp = Arrays.copyOfRange(bytes, i, i + 1000);
				} else {
					bytetemp = Arrays.copyOfRange(bytes, i, bytes.length);
				}
				_zipOut.write(bytetemp, 0, bytetemp.length);
			}
			_zipOut.closeEntry();
			_zipOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 压缩文件或路径
	 * 
	 * @param zip
	 *            压缩的目的地址
	 * @param srcFiles
	 *            压缩的源文件
	 */
	public static void zipFile(String zip, List<File> srcFiles) {
		try {
			if (zip.endsWith(".zip") || zip.endsWith(".ZIP") || zip.endsWith(".bin")) {
				File filezip=new File(zip);
				if(!filezip.getParentFile().exists()){
					filezip.getParentFile().mkdirs();
				}
				ZipOutputStream _zipOut = new ZipOutputStream(new FileOutputStream(filezip));
				_zipOut.setEncoding("GBK");
				for (File _f : srcFiles) {
					handlerFile(zip, _zipOut, _f, "");
				}
				_zipOut.close();
			} else {
				System.out.println("target file[" + zip + "] is not .zip type file");
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 *
	 * @param zip
	 *            压缩的目的地址
	 * @param zipOut
	 * @param srcFile
	 *            被压缩的文件信息
	 * @param path
	 *            在zip中的相对路径
	 * @throws IOException
	 */
	private static void handlerFile(String zip, ZipOutputStream zipOut, File srcFile, String path) throws IOException {
		System.out.println(" begin to compression file[" + srcFile.getName() + "]");
		if (!"".equals(path) && !path.endsWith(File.separator)) {
			path += File.separator;
		}
		if (!srcFile.getPath().equals(zip)) {
			if (srcFile.isDirectory()) {
				File[] _files = srcFile.listFiles();
				if (_files.length == 0) {
					zipOut.putNextEntry(new ZipEntry(path + srcFile.getName() + File.separator));
					zipOut.closeEntry();
				} else {
					for (File _f : _files) {
						handlerFile(zip, zipOut, _f, path + srcFile.getName());
					}
				}
			} else {
				InputStream _in = new FileInputStream(srcFile);
				zipOut.putNextEntry(new ZipEntry(path + srcFile.getName()));
				int len = 0;
				byte[] _byte = new byte[1024];
				while ((len = _in.read(_byte)) > 0) {
					zipOut.write(_byte, 0, len);
				}
				_in.close();
				zipOut.closeEntry();
			}
		}
	}

	/**
	 * 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
	 * 
	 * @param zipPath
	 *            待解压缩的ZIP文件名
	 * @param descDir
	 *            目标目录
	 */
	public static List<File> upzipFile(String zipPath, String descDir) {
		return upzipFile(new File(zipPath), descDir,false);
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
	@SuppressWarnings("rawtypes")
	public static List<File> upzipFile(File zipFile, String descDir,boolean isnewname) {
		List<File> _list = new ArrayList<File>();
		try {
			ZipFile _zipFile = new ZipFile(zipFile, "GBK");
			for (Enumeration entries = _zipFile.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				File _file = new File(descDir + File.separator + entry.getName().replaceAll("\\\\", "/"));
				if (entry.isDirectory()) {
					_file.mkdirs();
				} else {
					File _parent = _file.getParentFile();
					if (!_parent.exists()) {
						_parent.mkdirs();
					}
					InputStream _in = _zipFile.getInputStream(entry);
					if(isnewname){
						_file=new File(descDir,zipFile.getName());
					}						
					OutputStream  _out = new FileOutputStream(_file);
					int len = 0;
					byte[] _byte = new byte[1024];
					while ((len = _in.read(_byte)) > 0) {
						_out.write(_byte, 0, len);
					}
					_in.close();
					_out.flush();
					_out.close();
					_list.add(_file);
				}
			}
			_zipFile.close();
		} catch (IOException e) {
			System.out.println(zipFile.getPath());
			System.out.println(e.getMessage());
		}
		return _list;
	}

	/**
	 * 对临时生成的文件夹和文件夹下的文件进行删除
	 */
	public static void deletefile(String delpath) {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + File.separator + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
					} else if (delfile.isDirectory()) {
						deletefile(delpath + File.separator + filelist[i]);
					}
				}
				file.delete();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String zipPath = "K:\\rebim文件\\04.望哥透明度1.rebim";
		String descDir = "K:\\jiadan";
		upzipFile(zipPath, descDir);

	}

}