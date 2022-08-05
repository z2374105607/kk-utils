package net.kkppyy.utils.http;

import org.junit.Test;

/**
 * <p>Title:HttpToolTest</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author 张凯
 *
 * @date 2021年4月1日 上午11:05:16
 */

public class HttpToolTest {
	@Test
	public void sendGetTest(){
		for (int i = 0; i < 100; i++) {
			System.out.println(HttpTool.sendGet("http://172.16.0.222:8080/sofa/QRCode/getQRName?qid=424524cdc86148829548662935f83179"));
		}
	}
	@Test
	public void sendPostTest(){
		System.out.println(HttpTool.sendPost("http://172.16.0.222/sofa/QRCode/isComment",""));
	}
}
