package com.mcf.mybatis.mapper;

import java.util.List;

import com.mcf.bean.ArticleInRedis;
import com.mcf.mybatis.model.Article;

/**
 * Created by yl on 2015/8/27.
 */
public interface ArticleMapper {
    public boolean writeArticle(Article article);
    public Article readArticle(String id);
    public List<ArticleInRedis> getAllMyArticles(String openId);
}
