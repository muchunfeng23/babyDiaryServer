package com.mcf.bean;

import java.io.Serializable;

public class ArticleInRedis implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String intels;
	private String addDateStr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddDateStr() {
		return addDateStr;
	}
	public void setAddDateStr(String addDateStr) {
		this.addDateStr = addDateStr;
	}
	public String getIntels() {
		return intels;
	}
	public void setIntels(String intels) {
		this.intels = intels;
	}
}
