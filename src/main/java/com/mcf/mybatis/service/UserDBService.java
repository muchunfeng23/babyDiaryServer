package com.mcf.mybatis.service;

import com.mcf.mybatis.model.UserInfo;

public interface UserDBService {
	public void addUser(UserInfo user);

	public Integer isExist(String openId);

	public void addLoginLog(String openId);

	public String getRegisterDate(String openId);
}
