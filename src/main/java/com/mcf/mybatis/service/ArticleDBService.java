package com.mcf.mybatis.service;

import java.util.List;

import com.mcf.bean.ArticleInRedis;
import com.mcf.mybatis.model.Article;

public interface ArticleDBService {
	public boolean writeArticle(Article article);

	public Article readArticle(String id);

	public List<ArticleInRedis> getAllMyArticles(String openId);
}
