package com.mcf.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mcf.bean.ArticleInRedis;
import com.mcf.dao.ArticleDao;
import com.mcf.mybatis.mapper.ArticleMapper;
import com.mcf.mybatis.model.Article;
import com.mcf.mybatis.service.ArticleDBService;
import com.mcf.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	@Autowired
	private ArticleDBService articleDBService;
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public String writeArticle(Article article) {
		//写数据库
		articleDBService.writeArticle(article);
		//写redis
		articleDao.addAArticleSnapShot(article);
		return "ok";
	}

	@Override
	public Article readArticle(String id) {
		return articleDBService.readArticle(id);
	}

	@Override
	public List<ArticleInRedis> getAllMyArticleSnap(String openId) {
		List<ArticleInRedis> allMyArticles = articleDao.getArticlesInRedis(openId);
		if(allMyArticles == null){
			logger.info("从redis中获得文章列表失败，从数据库中获得");
			allMyArticles = articleDBService.getAllMyArticles(openId);
			if(allMyArticles != null){
				articleDao.addArticlesInRedis(openId, allMyArticles);
			}
		}
		return allMyArticles;
	}

}
