package com.mcf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import com.google.gson.Gson;
import com.mcf.bean.ArticleInRedis;
import com.mcf.mybatis.model.Article;
import com.mcf.service.ArticleService;
import com.mcf.util.dateTime.DateUtil;

@RestController
public class ArticleController {
	private Logger logger = LoggerFactory.getLogger(ArticleController.class);
	private Gson gson = new Gson();
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/writeArticle")
	public String uploadArticle(HttpServletRequest request,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("audios") String audioNames,
			@RequestParam("pics") String picNames,
			@RequestParam("intels") String intels
			){
		String openId = (String)request.getAttribute("openId");
		
		Article article = new Article();
		article.setId(UUID.randomUUID().toString());
		article.setOpenId(openId);
		article.setTitle(title);
		article.setContent(content);
		article.setAudios(audioNames);
		article.setPics(picNames);
		article.setIntels(intels);
		Date now = new Date(System.currentTimeMillis());
		article.setAddDate(now);
		article.setAddDateStr(DateUtil.format(now, "yyyy-MM-dd"));
		logger.info("写文章：" + "article = " + gson.toJson(article));
		articleService.writeArticle(article);
		return "ok";
	}
	
	@RequestMapping(value="/uploadImgs",method=RequestMethod.POST)
	public String uploadImgs(HttpServletRequest request,@RequestParam("uploadFileData") MultipartFile file){
		String fileName = request.getParameter("fileName");
		String openId = (String)request.getAttribute("openId");
		try{
			FileUtils.writeByteArrayToFile(new File("/data/uploadImages/babyDiary/" + openId + "/" + 
					fileName), file.getBytes());
			logger.info("插入图片成功 ..." + fileName);
			return "ok";
		}catch(IOException e){
			logger.error(e.getMessage());
			return "wrong";
		}
	}
	
	@RequestMapping(value="/uploadAudio",method=RequestMethod.POST)
	public String uploadAudio(HttpServletRequest request,@RequestParam("uploadFileData") MultipartFile file){
		String fileName = request.getParameter("fileName");
		String openId = (String)request.getAttribute("openId");
		try{
			FileUtils.writeByteArrayToFile(new File("/data/uploadImages/babyDiary/" + openId + "/" +
					fileName), file.getBytes());
			logger.info("插入音频成功 ..." + fileName);
			return "ok";
		}catch(IOException e){
			logger.error(e.getMessage());
			return "wrong";
		}
	}
	
	@RequestMapping("/downloadSth")
	public String download(HttpServletRequest request,HttpServletResponse response) {
		String openId = (String)request.getAttribute("openId");
		String fileName = request.getParameter("fileName");
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		try {
			logger.info("开始下载图片...");
			InputStream inputStream = new FileInputStream(new File("/data/uploadImages/babyDiary/" + openId + "/" + fileName));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			 // 这里主要关闭。
			os.close();
			inputStream.close();
			logger.info("下载图片完成...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	@RequestMapping(value="readArticle",produces="text/plain;charset=UTF-8")
	public String readArticle(HttpServletRequest request,@RequestParam("id") String id){
		Article article = articleService.readArticle(id);
		if(article == null){
			return "null";
		}
		String result = gson.toJson(article);
		logger.info("readArticle = " + result);
		return result;
	}
	
	@RequestMapping(value="readAllMyArticles",produces="text/plain;charset=UTF-8")
	public String readAllMyArticles(HttpServletRequest request){
		logger.info("readAllMyArticles start...");
		String openId = (String)request.getAttribute("openId");
		List<ArticleInRedis> allMyArticles = articleService.getAllMyArticleSnap(openId);
		if(allMyArticles != null){
			logger.info("readAllMyArticles end ... 获得几篇文章：" + allMyArticles.size());
		}
		String result = gson.toJson(allMyArticles);
		return result;
	}
}
