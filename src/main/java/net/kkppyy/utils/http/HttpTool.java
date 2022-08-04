package net.kkppyy.utils.http;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title:
 * @Description： Http请求工具
 * @author: 高举
 * @Date: 2020/8/28 15:23
 * @Version: 1.0
 */
public class HttpTool {

    private static Logger logger = LoggerFactory.getLogger(HttpTool.class);

    private HttpTool() {}

    /**
     * POST请求
     *
     * @param url       url
     * @param paramJson 参数的json格式
     */
    public static String sendPost(String url, String paramJson) {
        logger.info("开始发起POST请求，请求地址为{}，参数为{}", url, paramJson);

        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(url);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        try {
            //json格式的参数解析
            RequestEntity entity = new StringRequestEntity(paramJson, "application/json", "UTF-8");
            postMethod.setRequestEntity(entity);

            httpClient.executeMethod(postMethod);
            InputStream inputStream=postMethod.getResponseBodyAsStream();
            byte[] buffer = new byte[1024 * 1024 * 5];
            String path = null;
            int length=0;
            while ((length = inputStream.read(buffer)) > 0) {
                path = new String(buffer);
            }
            logger.info("Response of service: " + path);
            //inputStream.
            //String result = postMethod.getResponseBodyAsStream();
            postMethod.releaseConnection();
            return path;
        } catch (IOException e) {
            logger.error("POST请求发出失败，请求的地址为{}，参数为{}，错误信息为{}", url, paramJson, e.getMessage(), e);
        }
        return null;
    }

    /**
     * GET请求
     *
     * @param urlParam url请求，包含参数
     */
    public static String sendGet(String urlParam) {
        logger.info("开始发起GET请求，请求地址为{}", urlParam);
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(urlParam);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Content-Type", "application/json");
        try {
            httpClient.executeMethod(getMethod);
            String result = getMethod.getResponseBodyAsString();
            getMethod.releaseConnection();
            logger.info("返回信息为{}", result);
            return result;
        } catch (IOException e) {
            logger.error("GET请求发出失败，请求的地址为{}，错误信息为{}", urlParam, e.getMessage(), e);
        }
        return null;
    }
}
