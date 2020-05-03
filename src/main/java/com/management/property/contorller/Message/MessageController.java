package com.management.property.contorller.Message;

import com.management.property.Service.MessageCenterService;
import com.management.property.pojo.MessageCenter;
import com.management.property.shiro.PageUtil;
import com.management.property.shiro.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("Message")
public class MessageController {//消息中心
    @Autowired
    MessageCenterService messageCenterService;

    @GetMapping("index")

    public String IndexPage(){
        return "MessageCenter/message_index";
    }

    @RequestMapping("List")
    @ResponseBody
    public PageUtil<MessageCenter> strList(Integer limit, Integer page) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象

        List<MessageCenter> sysRaiseList = messageCenterService.selectAll(limit, page,login.getUserId());
        return new PageUtil<>(sysRaiseList, messageCenterService.count());
    }

    @PostMapping("updata")
    @ResponseBody
    public Integer strList(Integer id) {
        Integer result = messageCenterService.deleteByPrimaryKey(id);
        return result;
    }

}
