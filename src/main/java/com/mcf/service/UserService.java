package com.mcf.service;

import com.mcf.mybatis.model.UserInfo;

/**
 * Created by zl on 2015/8/27.
 */

public interface UserService {

    public void addAUser(UserInfo user);
    
    public boolean isExist(String openId);
    public void addLoginLog(String openId);
    public String getRegisterDateStr(String openId);
}
