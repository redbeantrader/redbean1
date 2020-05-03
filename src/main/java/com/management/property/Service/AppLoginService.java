package com.management.property.Service;

import com.management.property.shiro.PageUtil;
import org.springframework.stereotype.Service;

public interface AppLoginService {

    public PageUtil LoginLogic(String username,String password);

    public PageUtil Registered(String username,String password,String phone);
}
