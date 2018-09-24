package com.mcf.dao;

import com.mcf.bean.OpenIdAndSessionKey;

public interface SessionDao{
	//通过openId创建一个session,返回thirdSession
	public String createASession(String openId,String sessionKey);
	public OpenIdAndSessionKey getOpenIdAnsSessionKeyByThirdSession(String thirdSession);
	public boolean updateSessionTime(String thirdSession);
}
