package com.mcf.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class HttpUtil {
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	private static Gson gson = new Gson();
	public static String httpsRequest(String requestUrl,String requestMethod,String transferData){
		StringBuilder sb = new StringBuilder();
		try{
			//使用自定义的信任管理器
			TrustManager[] tm = {new YLX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			//建立连接
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			//设置请求方式
			conn.setRequestMethod(requestMethod);
			//当 传入的参数输出流不为null时，向输出流写数据
			if(null != transferData){
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(transferData.getBytes("UTF-8"));
				outputStream.close();
			}
			
			//从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			//读取响应内容
			String str = null;
			while((str = bufferedReader.readLine()) != null){
				sb.append(str);
			}
			//关闭/释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.info(sb.toString());
		return sb.toString();
	}
	
	public static JSONObject httpsRequest1(String requestUrl,String requestMethod,String outputStr){
		String fromUrlContent = httpsRequest(requestUrl,requestMethod,outputStr);
		JSONObject jsonObject = JSONObject.fromObject(fromUrlContent);
		return jsonObject;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source){
		String result = null;
		try{
			result = URLEncoder.encode(source,"utf-8");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return result;
	}
}
