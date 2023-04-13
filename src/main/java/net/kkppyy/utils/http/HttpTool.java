package net.kkppyy.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;
//import org.apache.commons.httpclient.methods.StringRequestEntity;
//import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Title: @Description： Http请求工具
 * @author: 高举
 * @Date: 2020/8/28 15:23
 * @Version: 1.0
 */
public class HttpTool {

	private static Logger logger = LoggerFactory.getLogger(HttpTool.class);

	private HttpTool() {
	}

	/**
	 * POST请求
	 *
	 * @param url
	 *            url
	 * @param paramJson
	 *            参数的json格式
	 */
	public static String sendPost(String url, String paramJson) {
		String CONTENT_TYPE_TEXT_JSON = "text/json";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(10000).build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

		httpPost.setConfig(requestConfig);
		StringEntity se = null;
		try {
			System.out.println(paramJson);
			se = new StringEntity(paramJson);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		se.setContentType(CONTENT_TYPE_TEXT_JSON);
		httpPost.setEntity(se);

		CloseableHttpResponse response = null;
		String result = null;
		try {
			response = httpClient.execute(httpPost);

			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * GET请求
	 *
	 * @param urlParam
	 *            url请求，包含参数
	 */
	public static String sendGet(String urlParam) {
		logger.info("开始发起GET请求，请求地址为{}", urlParam);
		// 1.获得一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 2.生成一个get请求
		HttpGet httpget = new HttpGet(urlParam);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
		// 设置请求和传输超时时间

		httpget.setConfig(requestConfig);
		// 3.执行get请求并返回结果
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			HttpEntity responseEntity = response.getEntity();
			logger.info("响应状态：" + response.getStatusLine());
			// gzip,deflate,compress
			logger.info("响应体编码方式：" + responseEntity.getContentEncoding());
			// 响应类型如text/html charset也有可能在ContentType中
			logger.info("响应体类型：" + responseEntity.getContentType());
			String result = EntityUtils.toString(responseEntity);
			logger.info("响应体内容：" + result);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (response != null)
					// 关闭连接，则此次连接被丢弃
					response.close();
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
}
