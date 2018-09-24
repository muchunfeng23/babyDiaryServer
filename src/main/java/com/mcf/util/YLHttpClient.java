package com.mcf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 类使用方法来自读文档：http://hc.apache.org/httpcomponents-client-ga/
 * all examples: http://hc.apache.org/httpcomponents-client-ga/examples.html
 * 
 * @author wjs
 *
 */
public class YLHttpClient {
	private static Logger logger = Logger.getLogger(YLHttpClient.class);
	public static String doGetRequest(String urlstr){
		CloseableHttpResponse response1 = null;
		String jsonResult = null;
		try{
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(urlstr);
			response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager. 
			logger.info(response1.getStatusLine());
			
			
			HttpEntity entity1 = response1.getEntity();
		    jsonResult = readInputStreamContent(entity1);
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity1);
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
		}catch(Exception e2){
			logger.error(e2.getMessage());
		}finally {
		    try {
				response1.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return jsonResult;
	}
	
	private static String readInputStreamContent(HttpEntity entity1) throws Exception{
		InputStream inputStream = entity1.getContent();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		//读取响应内容
		String str = null;
		StringBuilder sb = new StringBuilder();
		while((str = bufferedReader.readLine()) != null){
			sb.append(str);
		}
		//关闭/释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		return sb.toString();
	}
	
	public static void doPostRequest(String urlstr,List <NameValuePair> nvps){
		CloseableHttpResponse response2 = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(urlstr);
//			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//			nvps.add(new BasicNameValuePair("username", "vip"));
//			nvps.add(new BasicNameValuePair("password", "secret"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			response2 = httpclient.execute(httpPost);
			logger.info(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		}catch(IOException e1){
			logger.error(e1.getMessage());
		}finally{
			try {
				response2.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	public static void main(String args[]){
		System.out.println(YLHttpClient.doGetRequest("http://www.baidu.com"));
	}
}
