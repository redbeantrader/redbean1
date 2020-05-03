package com.management.property.contorller.UserInformation;

import com.management.property.shiro.pojo.User;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("UserInformation")
public class UserInformationController {//用户信息
    @Autowired
    UserService userService;

    @GetMapping("Information")
    public String ResulePage(){
        return "UserInformation/userinformation";
    }

    @GetMapping("infor")
    @ResponseBody
    public User ResultUser(){
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        User user = userService.selectByPrimaryKey(login.getUserId());
        return user;
    }

    @PostMapping("updata")
    @ResponseBody
    public Integer Updata(User user){
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        User userOld = userService.selectByPrimaryKey(login.getUserId());
        userOld.setUserNumber(user.getUserNumber());
        userOld.setPhone(user.getPhone());
        userOld.setUserName(user.getUserName());
        Integer result = userService.updateByPrimaryKey(userOld);
        return result;
    }

    @GetMapping("PEdit")
    public String PEditPage(){
        return "UserInformation/PasswordEdit";
    }

    @PostMapping("Pedit")
    @ResponseBody
    public Integer GoToPedit(String oldLP,String LoginPassword,String reLoginPassword){
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        Object com = Encrypt(login.getLoginName(),oldLP);

        if(!com.toString().equals(login.getLoginPassword())){
            return 3;
        }else if(!LoginPassword.equals(reLoginPassword)){
            return 2;
        }else {
            login.setLoginPassword(com.toString());
            Integer result = userService.updateByPrimaryKey(login);
            return result;
        }
    }

    public Object Encrypt(String LoginName,String LoginPassword){//加密方法
        Object password = LoginPassword;
        String hashAlgorithmName= "MD5";
        Object salt = LoginName;
        int hashIteration = 1024;
        Object result = new SimpleHash(hashAlgorithmName,password,salt,hashIteration);//加盐加密
        return result;
    }
}
