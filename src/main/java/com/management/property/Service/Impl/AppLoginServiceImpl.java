package com.management.property.Service.Impl;

import com.management.property.Service.AppLoginService;
import com.management.property.common.ResponseCode;
import com.management.property.shiro.PageUtil;
import com.management.property.shiro.pojo.User;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppLoginServiceImpl implements AppLoginService {//App用户端登陆
    @Autowired
    UserService userService;

    @Override
    public PageUtil LoginLogic(String username, String Lpassword) {//登录
        if(username==null||username.equals("")){//用户名为空
            return PageUtil.ServerResponseError(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if(Lpassword==null||Lpassword.equals("")){//密码为空
            return PageUtil.ServerResponseError(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }

        User user = userService.findByLoginName(username);
        if(user==null){//用户名不存在
            return PageUtil.ServerResponseError(ResponseCode.USERNAME_NOT_EXISTS.getCode(),ResponseCode.USERNAME_NOT_EXISTS.getMsg());
        }

        Object password = Lpassword;
        String hashAlgorithmName= "MD5";
        Object salt = username;
        int hashIteration = 1024;
        Object result = new SimpleHash(hashAlgorithmName,password,salt,hashIteration);//加盐加密
        if(!result.toString().equals(user.getLoginPassword())){//密码错误
            return PageUtil.ServerResponseError(ResponseCode.PASSWORD_ERROR.getCode(),ResponseCode.PASSWORD_ERROR.getMsg());
        }

        return PageUtil.ServerResponseSucess(user);
    }

    @Override
    public PageUtil Registered(String username, String password, String phone) {//注册
        if(username==null||username.equals("")){//用户名为空
            return PageUtil.ServerResponseError(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if(password==null||password.equals("")){//密码为空
            return PageUtil.ServerResponseError(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }
        if(phone==null||phone.equals("")){//手机号为空
            return PageUtil.ServerResponseError(ResponseCode.PHONE_NOT_EMPTY.getCode(),ResponseCode.PHONE_NOT_EMPTY.getMsg());
        }
        User user = userService.findByLoginName(username);
        if(user!=null){//用户已存在
            return PageUtil.ServerResponseError(ResponseCode.USERNAME_EXISTED.getCode(),ResponseCode.USERNAME_EXISTED.getMsg());
        }

        return PageUtil.ServerResponseSucess();
    }
}
