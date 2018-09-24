package com.mcf.mybatis.model;

import java.util.Date;

/**
 * {"nickName":"杨沐风","gender":1,"language":"zh_CN","city":"Haidian",
 * "province":"Beijing","country":"CN",
 * "avatarUrl":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIHI3Dx41Iialtqu1ibibP89LFsicsZ0MsZU02xvv5UibPDKKYiauOXENzS2QyuwOoic7gQhlaGAmNsLnDUQ/0"}
 * @author wjs
 *
 */
public class UserInfo {
	private String openId;
	private String nickName;
	private int gender;
	private String language;
	private String city;
	private String province;
	private String country;
	private String avatarUrl;
	private Date registerDate;
	private String registerDateStr;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterDateStr() {
		return registerDateStr;
	}
	public void setRegisterDateStr(String registerDateStr) {
		this.registerDateStr = registerDateStr;
	}
	
}
