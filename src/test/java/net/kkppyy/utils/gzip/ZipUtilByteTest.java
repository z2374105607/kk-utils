package net.kkppyy.utils.gzip;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.kkppyy.utils.file.ReadbytesUtil;
import net.kkppyy.utils.file.WriteBytesUtil;


public class ZipUtilByteTest {
	@Test
	public void upzipFileTest() {
		byte[] bytes = ReadbytesUtil.readFromByteFile("G:/filemanage/test/四个小桌子.rebim");
		Map<String, byte[]> map = ZipUtilByte.upzipFile(bytes, false);
		byte[] camera =map.get("camera.json");
		String cameraStr=null;
		cameraStr = new String(camera);
		System.out.println("cameraStr:"+cameraStr);
		
		/*Set<Entry<String, byte[]>> set = map.entrySet();
		for (Entry<String, byte[]> entry : set) {
			if (0 < entry.getKey().indexOf("/")) {
				File file = new File("G:/filemanage/test", entry.getKey().split("/")[0]);
				if (!file.exists()) {
					file.mkdirs();
				}
			}
			WriteBytesUtil.writeBytesUtil(entry.getValue(), entry.getKey(), "G:/filemanage/test");
		}*/
	}
	@Test
	public void upzipFileByteTest() {
		byte[] bytes = ReadbytesUtil.readFromByteFile("G:/filemanage/test/四个小桌子.rebim");
		byte[] camera = ZipUtilByte.upzipFileByte(bytes, "camera.json");
		String cameraStr=null;
		cameraStr = new String(camera);
		System.out.println("cameraStr:"+cameraStr);
	}
	@Test
	public void upzipFileNameTest() {
		byte[] bytes = ReadbytesUtil.readFromByteFile("G:/filemanage/test/四个小桌子.rebim");
		Map<String, byte[]> map = ZipUtilByte.upzipFileName(bytes);
		byte[] camera =map.get("camera.json");
		String cameraStr=null;
		cameraStr = new String(camera);
		System.out.println("cameraStr:"+cameraStr);
	}
	@Test
	public void byteToZipTest(){
		Map<String,byte[]> listbytes=new HashMap<String,byte[]>();
		listbytes.put("hello1","hello1".getBytes());
		listbytes.put("hello1/hello2","hello2".getBytes());
		listbytes.put("hello1/hello2/hello3","hello3".getBytes());
		byte[] model=ZipUtilByte.byteToZip(listbytes);
		WriteBytesUtil.writeBytesUtil(model,"modeltest.zip","G:/filemanage/test");
	}
}
