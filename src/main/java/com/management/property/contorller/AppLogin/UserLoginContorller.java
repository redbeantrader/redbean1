package com.management.property.contorller.AppLogin;

import com.management.property.Service.AppLoginService;
import com.management.property.common.ResponseCode;
import com.management.property.shiro.PageUtil;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/App/")
public class UserLoginContorller {

    @Autowired
    UserService userService;
    @Autowired
    AppLoginService appLoginService;
    @Autowired
    UserRoleService userRoleService;

    @RequestMapping("login")
    public PageUtil Login(String username,String password, HttpServletRequest request){

        if(username==null||username.equals("")){//用户名为空
            return PageUtil.ServerResponseError(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if(password==null||password.equals("")){//密码为空
            return PageUtil.ServerResponseError(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }

        User user = userService.findByLoginName(username);

        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 3.执行登录方法
        try {
            subject.login(token);
            SecurityUtils.getSubject().getPrincipal();
            request.setAttribute("User",user);
            return PageUtil.ServerResponseSucess(0,"登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return PageUtil.ServerResponseError(ResponseCode.USERNAME_NOT_EXISTS.getCode(),ResponseCode.USERNAME_NOT_EXISTS.getMsg());
        } catch (IncorrectCredentialsException e) {
            return PageUtil.ServerResponseError(ResponseCode.PASSWORD_ERROR.getCode(),ResponseCode.PASSWORD_ERROR.getMsg());
        }

    }

    @RequestMapping("reg")
    public PageUtil registered(String username,String password,String phone){
        PageUtil re = appLoginService.Registered(username,password,phone);
        if(re.getCode() == 0){
            Object resultPassword = Encrypt(username, password);//加盐加密

            User newUser = new User();//User存储用户
            newUser.setLoginName(username);
            newUser.setLoginPassword(resultPassword.toString());
            newUser.setPhone(phone);

            newUser.setUserStatus(1);
            Integer result = userService.insert(newUser);

            if (result == 1) {//UserRole添加角色
                User selectUser = userService.findByLoginName(username);
                UserRole newUserRole = new UserRole();
                newUserRole.setRoleId(3);
                newUserRole.setUserId(selectUser.getUserId());
                Integer result2 = userRoleService.insert(newUserRole);
                if(result2 == 1){
                    return PageUtil.ServerResponseError(0,"注册成功");
                }else
                    return PageUtil.ServerResponseError(1,"角色添加失败");
            }
            else
                return PageUtil.ServerResponseError(1,"注册失败");
        }else
            return re;
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
