package com.management.property.contorller.Payment;

import com.management.property.Service.*;
import com.management.property.pojo.MessageCenter;
import com.management.property.pojo.Pay;
import com.management.property.pojo.TypeInformation;
import com.management.property.pojo.Unpaid;
import com.management.property.shiro.PageUtil;
import com.management.property.shiro.pojo.User;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Payment")
public class PaymentController {//缴费系统
    @Autowired
    PayService payService;
    @Autowired
    CostsService costsService;
    @Autowired
    UserService userService;
    @Autowired
    TypeInformationService typeInformationService;
    @Autowired
    UnpaidService unpaidService;
    @Autowired
    MessageCenterService messageCenterService;

    @RequestMapping("comList")
    @ResponseBody
    public PageUtil<Pay> ComList(Integer limit, Integer page) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        List<Pay> sysRaiseList = payService.selectAllByIdandWhether(limit, page,login.getUserId(),"已缴费");
        for (int i=0;i<sysRaiseList.size();i++){
            sysRaiseList.get(i).setCostsType(costsService.selectByPrimaryKey(sysRaiseList.get(i).getCostsId()).getCostsType());
        }
        return new PageUtil<>(sysRaiseList, payService.count());
    }

    @RequestMapping("comListNot")
    @ResponseBody
    public PageUtil<Pay> ComListNot(Integer limit, Integer page) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        List<Pay> sysRaiseList = payService.selectAllByIdandWhether(limit, page,login.getUserId(),"未缴费");
        for (int i=0;i<sysRaiseList.size();i++){
            sysRaiseList.get(i).setCostsType(costsService.selectByPrimaryKey(sysRaiseList.get(i).getCostsId()).getCostsType());
        }
        return new PageUtil<>(sysRaiseList, payService.count());
    }

    @GetMapping("index")
    public String reIndex(){
        return "Payment/payment_index";
    }

    @GetMapping("not")
    public String reNot(){
        return "Payment/payment_not";
    }

    @GetMapping("look")
    public String reLook(){
        return "Payment/look";
    }

    @GetMapping("lookpay")
    public String lookPay(){
        return "Payment/lookPage";
    }

    @RequestMapping("ListNot")
    @ResponseBody
    public PageUtil<Unpaid> RList(Integer limit, Integer page) {

        List<Unpaid> sysRaiseList = unpaidService.selectAll(limit, page);
        return new PageUtil<>(sysRaiseList, unpaidService.count());
    }

    @RequestMapping("List")
    @ResponseBody
    public PageUtil<Pay> RListNot(Integer limit, Integer page) {
        List<Pay> sysRaiseList = payService.selectAll(limit, page,"已缴费");
        for (int i=0;i<sysRaiseList.size();i++){
            sysRaiseList.get(i).setUserName(userService.selectByPrimaryKey(sysRaiseList.get(i).getUserId()).getUserName());
            sysRaiseList.get(i).setCostsType(costsService.selectByPrimaryKey(sysRaiseList.get(i).getCostsId()).getCostsType());
        }
        return new PageUtil<>(sysRaiseList, payService.count());
    }

    @PostMapping("delete")
    @ResponseBody
    public Integer DeletePay(Integer id){
        Integer DelpayId = payService.deleteByPrimaryKey(id);
        return DelpayId;
    }

    @PostMapping("paying")
    @ResponseBody
    public Integer goPaying(Integer id){
        Float num = 0F;
        Pay selePayId = payService.selectByPrimaryKey(id);
        User user = userService.selectByPrimaryKey(selePayId.getUserId());
        TypeInformation ty = typeInformationService.selectByPrimaryKey(user.getUserRent());
        if(id.equals(4)){
            ty.setInternet(1);
        }else if(id.equals(5)){
            ty.setWcut(1);
        }else if(id.equals(6)){
            ty.setEcut(1);
        } else if(id.equals(7)){
            ty.setGcut(1);
        }
        typeInformationService.updateByPrimaryKey(ty);

        Date time = new Date();//获取当前时间
        Timestamp timestamp = new Timestamp(time.getTime());
        selePayId.setPayDate(timestamp);

        selePayId.setPayWhether("已缴费");
        Integer DelpayId = payService.updateByPrimaryKey(selePayId);

        Unpaid un = unpaidService.selectByPrimaryKey(selePayId.getUserId());
        if(un != null){
            List<Pay> sele = payService.selectByUserId(selePayId.getUserId(),"未缴费");
            if(sele == null){
                unpaidService.deleteByPrimaryKey(selePayId.getUserId());
            }
            else {
                for(int i=0;i<sele.size();i++){
                    num=sele.get(i).getPayMoeny()+num;
                }
                un.setPaymoeny(num);
                unpaidService.updateByPrimaryKey(un);
            }
        }

        return DelpayId;
    }

    @PostMapping("payingList")
    @ResponseBody
    public Integer goPayingList(@RequestParam(value ="id") List<Integer> id){
        Integer DelpayId = 0;
        Float num = 0F;

        Pay selePayId = payService.selectByPrimaryKey(id.get(0));
        User user = userService.selectByPrimaryKey(selePayId.getUserId());
        TypeInformation ty = typeInformationService.selectByPrimaryKey(user.getUserRent());

        Date time = new Date();//获取创建时间
        Timestamp timestamp = new Timestamp(time.getTime());

        for(int i=0; i<id.size();i++){
            Pay selePay = payService.selectByPrimaryKey(id.get(i));

            if(selePay.getCostsId().equals(4)){
                ty.setInternet(1);
            }else if(selePay.getCostsId().equals(5)){
                ty.setWcut(1);
            }else if(selePay.getCostsId().equals(6)){
                ty.setEcut(1);
            } else if(selePay.getCostsId().equals(7)){
                ty.setGcut(1);
            }

            selePayId.setPayWhether("已缴费");
            selePayId.setPayDate(timestamp);
            DelpayId = payService.updateByPrimaryKey(selePayId);
        }

        typeInformationService.updateByPrimaryKey(ty);//保存断水或者断电之类

        Unpaid un = unpaidService.selectByPrimaryKey(selePayId.getUserId());
        if(un != null){
            List<Pay> sele = payService.selectByUserId(selePayId.getUserId(),"未缴费");
            if(sele == null){
                unpaidService.deleteByPrimaryKey(selePayId.getUserId());
            }
            else {
                for(int i=0;i<sele.size();i++){
                    num=sele.get(i).getPayMoeny()+num;
                }
                un.setPaymoeny(num);
                unpaidService.updateByPrimaryKey(un);
            }
        }
        return DelpayId;
    }

    @RequestMapping("find")
    public String FindInformation(){
        return "Payment/findPayIn";
    }

    @PostMapping("findPayInformation")
    @ResponseBody
    public Map FindPayInformation(Integer id){
        Map map = new HashMap();
        User user = userService.selectByPrimaryKey(id);
        List<Pay> pay = payService.UserIdSelect(id);
        TypeInformation result = typeInformationService.selectByPrimaryKey(user.getUserRent());
        map.put("pay",pay);
        map.put("result",result);
        return map;
    }

    @PostMapping("findset")
    @ResponseBody
    public Integer FindSet(Integer id,Integer a,Integer b,Integer c,Integer d){
        TypeInformation select = typeInformationService.selectByPrimaryKey(id);
        select.setInternet(c);
        select.setWcut(a);
        select.setEcut(b);
        select.setGcut(d);
        Integer result = typeInformationService.updateByPrimaryKey(select);
        return result;
    }

    @PostMapping("sendInformation")
    @ResponseBody
    public Integer SendInformation(@RequestParam(value ="id")List<Integer> id){
        MessageCenter messageCenter = new MessageCenter();

        Date time = new Date();//获取创建时间
        Timestamp timestamp = new Timestamp(time.getTime());
        Integer result = 0;

        for(int i=0;i<id.size();i++){
            messageCenter.setUserId(id.get(i));
            messageCenter.setMessageContent("您有费用未缴，请尽快缴费！");
            messageCenter.setMessageStatus("未读");
            messageCenter.setMessageTime(timestamp);
            messageCenter.setTitle("缴费通知");
            messageCenter.setMessageType("缴费");
            messageCenter.setPromulgatorId(1);
            result = messageCenterService.insert(messageCenter);
        }

        return result;
    }

}
