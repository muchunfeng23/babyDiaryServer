package com.mcf.mybatis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcf.bean.ArticleInRedis;
import com.mcf.mybatis.mapper.ArticleMapper;
import com.mcf.mybatis.model.Article;
import com.mcf.mybatis.service.ArticleDBService;

@Service
public class ArticleDBServiceImpl implements ArticleDBService{
	private Logger logger = LoggerFactory.getLogger(ArticleDBServiceImpl.class);
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public boolean writeArticle(Article article) {
		//直接在客户端用<text>接收，能识别\n换行符
//		String content = article.getContent();
//		if(content != null){
//			logger.info("content = " + content);
//			content = content.replaceAll("\n", "<text>\\n</text>");
//			article.setContent(content);
//		}
		return articleMapper.writeArticle(article);
	}

	@Override
	public Article readArticle(String id) {
		return articleMapper.readArticle(id);
	}

	@Override
	public List<ArticleInRedis> getAllMyArticles(String openId) {
		return articleMapper.getAllMyArticles(openId);
	}

}
