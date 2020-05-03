package com.management.property.shiro;

import com.management.property.shiro.mapper.RoleMapper;
import com.management.property.shiro.mapper.UserMapper;
import com.management.property.shiro.pojo.Role;
import com.management.property.shiro.pojo.User;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===执行授权===");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
           // 角色与权限字符串集合
            Collection<String> rolesCollection = new HashSet<String>();
            Role role=roleMapper.selectRoleByLoginName(((User) subject.getPrincipal()).getLoginName());
//            Collection<String> premissionCollection = new HashSet<String>();
//             读取并赋值用户角色与权限
//            Set<Role> roles = user.getUserAuthority();
//            for (Role role : roles) {
                rolesCollection.add(role.getRole());
//                Set<Role> permissions = role.getSysPermissionList();
//                for (SysPermission permission : permissions) {
//                    premissionCollection.add(permission.getUrl());
//                }
//                info.addStringPermissions(premissionCollection);
//            }
            info.addRoles(rolesCollection);
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("===执行认证===");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User bean = userService.findByLoginName(token.getUsername());

        if (bean == null) {
            throw new UnknownAccountException();
        }
        //盐值加密
        ByteSource credentialsSalt = ByteSource.Util.bytes(bean.getLoginName());
        return new SimpleAuthenticationInfo(bean, bean.getLoginPassword(), credentialsSalt, getName());
    }
}
