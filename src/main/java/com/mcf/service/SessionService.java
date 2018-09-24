package com.mcf.service;
import com.mcf.bean.OpenIdAndSessionKey;

public interface SessionService {
public String createASession(String openId,String sessionKey);
	
	public OpenIdAndSessionKey getSession(String thirdSessionKey);
	
	public String getOpenId(String thirdSessionKey);
	
	public boolean updateSessionTime(String thirdSessionKey);
}
