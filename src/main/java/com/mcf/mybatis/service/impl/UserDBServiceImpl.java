package com.mcf.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcf.mybatis.mapper.UserMapper;
import com.mcf.mybatis.model.UserInfo;
import com.mcf.mybatis.service.UserDBService;

@Service
public class UserDBServiceImpl implements UserDBService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void addUser(UserInfo user) {
		userMapper.addUser(user);
	}

	@Override
	public Integer isExist(String openId) {
		return userMapper.isExist(openId);
	}

	@Override
	public void addLoginLog(String openId) {
		userMapper.addLoginLog(openId);
	}

	@Override
	public String getRegisterDate(String openId) {
		return userMapper.getRegisterDate(openId);
	}

}
