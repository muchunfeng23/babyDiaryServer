package com.mcf.dao;

import java.util.List;

import com.mcf.bean.ArticleInRedis;
import com.mcf.mybatis.model.Article;

public interface ArticleDao {
	public List<ArticleInRedis> getArticlesInRedis(String openId);
	public boolean addAArticleSnapShot(Article article);
	public boolean addArticlesInRedis(String openId,List<ArticleInRedis> allArticles);
}
