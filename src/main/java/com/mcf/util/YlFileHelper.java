package com.mcf.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YlFileHelper {
	private static Logger logger = LoggerFactory.getLogger(YlFileHelper.class);

	// 利用request对象返回客户端来的输入流
	public static void writeAFile(HttpServletRequest request) {
		try {
			ServletInputStream sis = request.getInputStream();
			OutputStream os = new FileOutputStream("/data/images/babyDiary/1.jpg");
			BufferedOutputStream bos = new BufferedOutputStream(os);

			byte[] buf = new byte[1024];
			int length = 0;
			length = sis.readLine(buf, 0, buf.length);// 使用sis的读取数据的方法
			while (length != -1) {
				bos.write(buf, 0, length);
				length = sis.read(buf);
			}
			sis.close();
			bos.close();
			os.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public static String generateFileName(String fileType) {
		// 以每分钟为key
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		// hourOfYear 4位数
		// int
		// hourOfYear=9999-(calendar.get(Calendar.DAY_OF_YEAR)*24+calendar.get(Calendar.HOUR_OF_DAY));
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append(calendar.get(Calendar.MONTH));
		sb.append("/");
		sb.append(calendar.get(Calendar.DAY_OF_MONTH));
		sb.append("-");
		sb.append(calendar.get(Calendar.HOUR_OF_DAY));// 1位
		sb.append(calendar.get(Calendar.MINUTE));// 1位
		sb.append(calendar.get(Calendar.SECOND));// 1位
		sb.append("-");
		// sb.append(atomicInteger.getAndIncrement());
		// sb.append(RandomStringUtils.randomAlphanumeric(5));
		sb.append(".");
		sb.append(fileType);
		return sb.toString();
	}

}
