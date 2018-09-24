package com.mcf.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.util.UriEncoder;

import com.google.gson.Gson;
import com.mcf.ConfigInfo;
import com.mcf.bean.OpenIdAndSessionKey;
import com.mcf.mybatis.model.UserInfo;
import com.mcf.service.SessionService;
import com.mcf.service.UserService;
import com.mcf.util.YLHttpClient;
import com.mcf.util.dateTime.DateUtil;

@RestController
public class UserController {
private Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private SessionService sessionService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		String code = request.getParameter("code");
		//如果用户数据库中没有该用户，则用户表中插入该数据，并加上插入时间，有该用户则返回插入日期
		String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid=APPID"
				+ "&secret=SECRET"
				+ "&js_code=JSCODE"
				+ "&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", ConfigInfo.APP_ID);
		requestUrl = requestUrl.replace("JSCODE", code);
		requestUrl = requestUrl.replace("SECRET", ConfigInfo.APP_SECRET);
		//从微信服务器返回数据
		String jsonResult = YLHttpClient.doGetRequest(requestUrl);
		Gson gson = new Gson();
		OpenIdAndSessionKey openIdAndSessionKey = gson.fromJson(jsonResult, OpenIdAndSessionKey.class);
		if(openIdAndSessionKey == null){
			logger.error("code = " + code + "过期了");
			return null;
		}
		//判断是否存在openId,直接查的数据库,存在说明注册过，没存在说明需要注册
		boolean isExist = userService.isExist(openIdAndSessionKey.getOpenid());
		if(!isExist){
			String userInfoParamStr = request.getParameter("userInfo");
			String userInfoParam = UriEncoder.decode(userInfoParamStr);
			logger.info(userInfoParam + " " + jsonResult);
			UserInfo userInfo = gson.fromJson(userInfoParam, UserInfo.class);
			userInfo.setOpenId(openIdAndSessionKey.getOpenid());
			Date now = new Date(System.currentTimeMillis());
			String nowStr = DateUtil.format(now, "yyyy-MM-dd");
			userInfo.setRegisterDate(now);
			userInfo.setRegisterDateStr(nowStr);
			userService.addAUser(userInfo);
			logger.info("添加新用户：" + userInfo);
		}
		//增加登录日志
		userService.addLoginLog(openIdAndSessionKey.getOpenid());
		String thirdSession = sessionService.createASession(openIdAndSessionKey.getOpenid(), openIdAndSessionKey.getSession_key());
		return thirdSession;
	}
	
	@RequestMapping("/getRegisterDate")
	public String getRegisterDate(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		return userService.getRegisterDateStr(openId);
	}
}
