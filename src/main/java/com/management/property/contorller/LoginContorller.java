package com.management.property.contorller;

import com.management.property.shiro.pojo.User;
import com.management.property.shiro.pojo.UserRole;
import com.management.property.shiro.service.UserRoleService;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/")
public class LoginContorller {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("login")
    public String doLogin(String LoginName, String Lpassword, HttpServletRequest request) {//登录

        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(LoginName, Lpassword);
        // 3.执行登录方法
        try {
            subject.login(token);
            SecurityUtils.getSubject().getPrincipal();
            return "redirect:/main";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            request.setAttribute("msg", "用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            request.setAttribute("msg", "密码错误！");
        }
        return "login";
    }

    @RequestMapping("main")
    public String loginSuccess() {
        return "main";
    }

    @RequestMapping("logout")
    public String logout(){//退出
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/login";
    }

    @PostMapping("registered")
    @ResponseBody
    public Integer Registered(String loginName,String LoginPassword,String Phone){//注册

            if (userService.findByLoginName(loginName)!=null) {
                return 0;
            } else {
                Object resultPassword = Encrypt(loginName, LoginPassword);//加盐加密

                User newUser = new User();//User存储用户
                newUser.setLoginName(loginName);
                newUser.setLoginPassword(resultPassword.toString());
                newUser.setPhone(Phone);

                newUser.setUserStatus(1);
                Integer result = userService.insert(newUser);

                if (result == 1) {//UserRole添加角色
                    User selectUser = userService.findByLoginName(loginName);
                    UserRole newUserRole = new UserRole();
                    newUserRole.setRoleId(3);
                    newUserRole.setUserId(selectUser.getUserId());
                    return userRoleService.insert(newUserRole);
                } else
                    return 0;
            }
    }

    @PostMapping("GoToReset")
    @ResponseBody
    public Integer gotoReset(User user){//忘记密码
        User result = userService.findByLoginName(user.getLoginName());

        if(result!=null)
            if(result.getPhone().equals(user.getPhone())){
                Object resultPassword = Encrypt(user.getLoginName(),user.getLoginPassword());
                result.setLoginPassword(resultPassword.toString());
                Integer re = userService.updateByPrimaryKey(result);
                return re;
            }

        return 0;
    }

    public Object Encrypt(String LoginName,String LoginPassword){//加密方法
        Object password = LoginPassword;
        String hashAlgorithmName= "MD5";
        Object salt = LoginName;
        int hashIteration = 1024;
        Object result = new SimpleHash(hashAlgorithmName,password,salt,hashIteration);//加盐加密
        return result;
    }

    @RequestMapping("Accessible")
    public String Accessible(){//权限测试用跳转

        return "accessible";
    }

    @RequestMapping("NotSuccess")
    public String noPermission(){//无权限访问页面

        return "NoPermission";
    }

    @RequestMapping("reset")
    public String Reset(){//忘记密码跳转页面
        return "ResetPage";
    }



}
