package com.management.property.contorller.Feedback;


import com.management.property.Service.MessageCenterService;
import com.management.property.pojo.MessageCenter;
import com.management.property.shiro.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("Feedback")
public class FeedbackContriller {//意见投诉
    @Autowired
    MessageCenterService messageCenterService;

    @GetMapping("index")
    public String IndexPage(){
        return "Feedback/feedback_index";
    }

    @PostMapping("add")
    @ResponseBody
    public Integer gotoAdd(String infromation) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象

        Date time = new Date();//获取创建时间
        Timestamp timestamp = new Timestamp(time.getTime());

        MessageCenter messageCenter = new MessageCenter();
        messageCenter.setUserId(1);
        messageCenter.setMessageType("投诉");
        messageCenter.setMessageTime(timestamp);
        messageCenter.setMessageContent(infromation);
        messageCenter.setMessageStatus("未读");
        messageCenter.setPromulgatorId(login.getUserId());
        messageCenter.setTitle("用户"+login.getUserName()+"的消息");

        Integer result = messageCenterService.insert(messageCenter);

        return result;
    }

}
