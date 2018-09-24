package com.mcf.filter;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mcf.ConfigInfo;
import com.mcf.service.SessionService;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	@Autowired
	private SessionService sessionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		String requestUrl = request.getRequestURI();
		if(requestUrl.equals("/babyDiary/login")){
			return true;
		}else{
			String thirdSession = request.getParameter(ConfigInfo.THIRD_SESSION);
			if(thirdSession == null){
				response.getWriter().write("thirdSession error");
				return false;
			}
			if(sessionService.getSession(thirdSession) == null){
				response.getWriter().write("thirdSession error");
				logger.error("thirdSession out of date");
				return false;
			}else{
				String openId = sessionService.getOpenId(thirdSession);
				if(openId == null){
					logger.error("openid is null");
					return false;
				}
				logger.info("传递openId = " + openId);
				//更新thirdSession时间
				sessionService.updateSessionTime(thirdSession);
				request.setAttribute("openId", openId);
				return true;
			}
		}
		
//		return true;
	}
}
