package com.management.property.contorller;

import com.management.property.Service.*;
import com.management.property.pojo.Costs;
import com.management.property.pojo.Pay;
import com.management.property.pojo.TypeInformation;
import com.management.property.pojo.Unpaid;
import com.management.property.shiro.pojo.User;
import com.management.property.shiro.pojo.UserRole;
import com.management.property.shiro.service.UserRoleService;
import com.management.property.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Timing {
    @Autowired
    UserService userService;
    @Autowired
    CostsService costsService;
    @Autowired
    PayService payService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UnpaidService unpaidService;
    @Autowired
    MessageCenterService messageCenterService;
    @Autowired
    TypeInformationService typeInformationService;

    @Scheduled(cron = "0 0 0 1 * *")//定时刷新
    public void testSca(){
        Float[] a =new Float[]{144f,300f,15f,12f,25f,100f,100f};
        Float num = 0f;

        Date time = new Date();//获取当前时间
        Timestamp timestamp = new Timestamp(time.getTime());//缴费时间

        Calendar rightNow = Calendar.getInstance();//日历格式
        rightNow.setTime(time);

        int month = rightNow.get(Calendar.MONTH);

        rightNow.add(Calendar.MONTH,1);//日期加1个月，作为截止日期
        Date time0=rightNow.getTime();//转换成long型数据
        Timestamp timestamp1 = new Timestamp(time0.getTime());//截止日期

        List<User> user = userService.ALL();
        List<Costs> costs = costsService.CostsList();

        for(int i=0;i<user.size();i++){//找用户,准备写入缴费信息，Pay表
            UserRole Id = userRoleService.selectById(user.get(i).getUserId());//找权限ID
            if(Id.getRoleId().equals(2)){
                Pay pay = new Pay();
                for(int j = 0;j<costs.size();j++) {

                    if(costs.get(j).getCostsId().equals(2)){
                        Integer park = user.get(i).getParkingId();
                        if(park.equals("")){
                            continue;
                        }
                    }
                    pay.setUserId(user.get(j).getUserId());
                    pay.setPayWhether("未缴费");
                    pay.setPayDate(timestamp);
                    pay.setMaturityDate(timestamp1);
                    pay.setPayMonth(month);
                    pay.setPayMoeny(a[j]);
                    pay.setCostsId(costs.get(j).getCostsId());
                    payService.insert(pay);
                    }
                }
            else{
                continue;
            }
        }
        for(int i = 0;i<user.size();i++) {//Unpaid表数据
            UserRole Id = userRoleService.selectById(user.get(i).getUserId());//找权限ID
            if (Id.getRoleId().equals(2)) {//权限为2
                Unpaid unpaid = unpaidService.SelectByUserId(user.get(i).getUserId());
                TypeInformation typeInformation = typeInformationService.selectByPrimaryKey(user.get(i).getUserRent());

                Unpaid newUn = new Unpaid();
                newUn.setUserId(user.get(i).getUserId());
                newUn.setUserName(user.get(i).getUserName());
                newUn.setRegion(typeInformation.getRegion()+typeInformation.getNumber());
                newUn.setPaywhether("欠费");

                Unpaid un = unpaidService.selectByPrimaryKey(user.get(i).getUserId());
                if(un != null){
                    List<Pay> sele = payService.selectByUserId(user.get(i).getUserId(),"未缴费");
                    for(int k=0;k<sele.size();k++){
                        num=sele.get(k).getPayMoeny()+num;//将数据库中的欠费累加
                    }
                newUn.setPaymoeny(num);//写入
                newUn.setMaturitydate(timestamp1);

                if (unpaid == null) {//如果之前有欠费
                    unpaidService.updateByPrimaryKey(newUn);//更新数据
                    }else {
                    unpaidService.insert(newUn);
                    }
                }
            }else {
                continue;
            }
        }

    }

}
