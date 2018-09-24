package com.mcf.service;

import java.util.List;

import com.mcf.bean.ArticleInRedis;
import com.mcf.mybatis.model.Article;

public interface ArticleService {
	public String writeArticle(Article article);
	public Article readArticle(String id);
	
	public List<ArticleInRedis> getAllMyArticleSnap(String openId);
}
