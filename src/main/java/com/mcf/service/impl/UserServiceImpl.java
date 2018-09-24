package com.mcf.service.impl;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.mcf.mybatis.model.UserInfo;
import com.mcf.mybatis.service.UserDBService;
import com.mcf.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDBService userDBService;
    
    public void addAUser(UserInfo user){
    	userDBService.addUser(user);
    }
    
    public boolean isExist(String openId){
    	return userDBService.isExist(openId) > 0;
    }

    public void addLoginLog(String openId){
    	userDBService.addLoginLog(openId);
    }

	@Override
	public String getRegisterDateStr(String openId) {
		return userDBService.getRegisterDate(openId);
	}
}
