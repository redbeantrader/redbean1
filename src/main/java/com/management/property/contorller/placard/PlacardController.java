package com.management.property.contorller.placard;

import com.management.property.Service.TextService;
import com.management.property.pojo.Text;
import com.management.property.shiro.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("card")
public class PlacardController {//公告模块
    @Autowired
    TextService textService;

    @RequestMapping("result")
    @ResponseBody
    public PageUtil<Text> TextList(Integer limit, Integer page) {
        List<Text> sysRaiseList = textService.selectAll(limit, page);
        return new PageUtil<>(sysRaiseList, textService.count());
    }

    @GetMapping("add")
    public String ResulePage(){
        return "NewPlacard";
    }

    @GetMapping("edit")
    public String EditCard(){
        return "EditCard";
    }

    @PostMapping("delete")
    @ResponseBody
    public Integer DeleteCard(Integer id){
        Integer resule = textService.deleteByPrimaryKey(id);
        return resule;
    }


    @PostMapping("add")
    @ResponseBody
    public Integer AddText(Text text){
        Text textUse = new Text();

        Date time = new Date();//获取创建时间
        Timestamp timestamp = new Timestamp(time.getTime());
        textUse.setDate(timestamp);

        textUse.setProposePlace("管理员");
        textUse.setStatus("已发布");
        textUse.setType("公告");
        textUse.setTextContent(text.getTextContent());
        textUse.setTitle(text.getTitle());

        Integer resule = textService.insert(textUse);

        return resule;
    }

    @PostMapping("edit")
    @ResponseBody
    public Integer DoEdit(Text text){
        Text co = textService.selectByPrimaryKey(text.getId());
        co.setTitle(text.getTitle());
        co.setTextContent(text.getTextContent());
        Date time = new Date();//获取创建时间
        Timestamp timestamp = new Timestamp(time.getTime());
        co.setDate(timestamp);

        Integer resule = textService.updateByPrimaryKeySelective(co);

        return resule;
    }
}
