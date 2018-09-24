package com.mcf.mybatis.model;

import java.util.Date;

public class Article {
	private String id;
	private String openId;
	private String title;
	private String content;
	private String audios;
	private String pics;
	private String intels;
	private Date addDate;
	private String addDateStr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
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
	public String getAudios() {
		return audios;
	}
	public void setAudios(String audios) {
		this.audios = audios;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public String getIntels() {
		return intels;
	}
	public void setIntels(String intels) {
		this.intels = intels;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getAddDateStr() {
		return addDateStr;
	}
	public void setAddDateStr(String addDateStr) {
		this.addDateStr = addDateStr;
	}
	
}
