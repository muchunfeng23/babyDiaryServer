package com.mcf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcf.bean.OpenIdAndSessionKey;
import com.mcf.dao.SessionDao;
import com.mcf.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	private SessionDao sessionDao;
	
	@Override
	public String createASession(String openId, String sessionKey) {
		return sessionDao.createASession(openId, sessionKey);
	}

	@Override
	public OpenIdAndSessionKey getSession(String thirdSessionKey) {
		return sessionDao.getOpenIdAnsSessionKeyByThirdSession(thirdSessionKey);
	}

	@Override
	public String getOpenId(String thirdSessionKey) {
		OpenIdAndSessionKey openIdAndSessionKey = sessionDao.getOpenIdAnsSessionKeyByThirdSession(thirdSessionKey);
		if(openIdAndSessionKey != null){
			return openIdAndSessionKey.getOpenid();
		}
		return null;
	}
	
	@Override
	public boolean updateSessionTime(String thirdSessionKey) {
		return sessionDao.updateSessionTime(thirdSessionKey);
	}

}
