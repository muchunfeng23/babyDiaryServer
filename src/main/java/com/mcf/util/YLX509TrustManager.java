package com.mcf.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 客户端访问HTTPS链接时，有两种方法能够让JSSE信任HTTPS服务器端的安全证书：
 * 将HTTPS服务端的安全证书导入到客户端的TrustStore文件中
 * 实现自定义的信任管理器类
 * 
 * 下面是第二种
 * @author wjs
 *
 */
public class YLX509TrustManager implements X509TrustManager{
	//检查客户端证书
	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}

	//检查服务端证书
	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}

	//返回受信任的X509证书数组
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

//	/**
//	 * 下面是使用YLX509TrustManager类发送HTTPS请求的示例
//	 * @param args
//	 * @throws NoSuchProviderException 
//	 * @throws NoSuchAlgorithmException 
//	 * @throws KeyManagementException 
//	 * @throws IOException 
//	 */
//	public static void main(String args[]) throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, IOException{
//		String requestUrl = "";
//		//创建SSLContext对象，并使用我们指定的信任管理器初始化
//		TrustManager[] tm = {new YLX509TrustManager()};
//		//这儿针对的是SUN JVM，如果在IBM JVM下运行会报错，需要进行相应调整
//		SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
//		sslContext.init(null, tm, new java.security.SecureRandom());
//		//
//		SSLSocketFactory ssf = sslContext.getSocketFactory();
//		
//		URL url = new URL(requestUrl);
//		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
//		conn.setSSLSocketFactory(ssf);
//	}
}
