package com.mcf.dao.redisDaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.mcf.ConfigInfo;
import com.mcf.bean.ArticleInRedis;
import com.mcf.dao.ArticleDao;
import com.mcf.mybatis.model.Article;

@Component
public class ArticleDaoImpl implements ArticleDao{
	private Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);
	
	@Autowired
	private ValueOperations<Object,Object> valOps;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleInRedis> getArticlesInRedis(String openId) {
		// TODO Auto-generated method stub
		return (List<ArticleInRedis>)valOps.get(openId+ConfigInfo.ALL_ARTICLE);
	}

	@Override
	public boolean addArticlesInRedis(String openId, List<ArticleInRedis> allArticles) {
		if(allArticles == null){
			logger.info("往redis写文章列表失败，allArticles == null");
			return false;
		}
		valOps.set(openId+ConfigInfo.ALL_ARTICLE, allArticles);
		return true;
	}

	@Override
	public boolean addAArticleSnapShot(Article article) {
		String openId = article.getOpenId();
		@SuppressWarnings("unchecked")
		List<ArticleInRedis> allArticles = (List<ArticleInRedis>)valOps.get(openId+ConfigInfo.ALL_ARTICLE);
		if(allArticles == null){
			allArticles = new ArrayList<ArticleInRedis>();
		}
		ArticleInRedis articleInRedis = new ArticleInRedis();
		articleInRedis.setId(article.getId());
		articleInRedis.setTitle(article.getTitle());
		articleInRedis.setContent(article.getContent());
		articleInRedis.setAddDateStr(article.getAddDateStr());
		allArticles.add(articleInRedis);
		//这儿的方法肯定要修改，如果每次都要取出来，用户量一大，内存受不住
		valOps.set(openId+ConfigInfo.ALL_ARTICLE, allArticles);
		return true;
	}

}
