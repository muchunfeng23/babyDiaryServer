package com.mcf.dao.redisDaoImpl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mcf.bean.OpenIdAndSessionKey;
import com.mcf.dao.SessionDao;

@Component
public class SessionDaoImpl implements SessionDao, InitializingBean {
	private Logger logger = LoggerFactory.getLogger(SessionDaoImpl.class);
	private Gson gson = new Gson();
	
	@Autowired
	private ValueOperations<Object,Object> valOps;

	@Override
	public String createASession(String openId, String sessionKey) {
		// TODO Auto-generated method stub
		logger.debug("访问set:key={},value={}",openId,sessionKey);
		String thirdSessionKey = UUID.randomUUID().toString();
		logger.info("thirdSessionKey = " + thirdSessionKey);
		OpenIdAndSessionKey openIdAndSessionKey = new OpenIdAndSessionKey(openId,sessionKey);
		String openIdAndSessionKeyJson = gson.toJson(openIdAndSessionKey, OpenIdAndSessionKey.class);
		logger.info("openIdAndSessionKeyJson = " + openIdAndSessionKeyJson);
		valOps.set(thirdSessionKey,openIdAndSessionKeyJson, 60, TimeUnit.SECONDS);  
		return thirdSessionKey;
	}

	@Override
	public OpenIdAndSessionKey getOpenIdAnsSessionKeyByThirdSession(String thirdSession) {
		// TODO Auto-generated method stub
		String fromJson = (String)valOps.get(thirdSession);
		OpenIdAndSessionKey openIdAndSessionKey = gson.fromJson(fromJson, OpenIdAndSessionKey.class);
		logger.info("fromJson = " + fromJson + "  openIdAndSessionKey = " + openIdAndSessionKey);
		return openIdAndSessionKey;
	}

	@Override
	public boolean updateSessionTime(String thirdSession) {
		String fromJson = (String) valOps.get(thirdSession);
		valOps.set(thirdSession, fromJson, 60, TimeUnit.SECONDS);
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
