package com.management.property.contorller.House_information;

import com.management.property.Service.PayService;
import com.management.property.Service.TypeInformationService;
import com.management.property.pojo.Pay;
import com.management.property.pojo.TypeInformation;
import com.management.property.shiro.PageUtil;
import com.management.property.shiro.pojo.User;
import com.management.property.shiro.pojo.UserRole;
import com.management.property.shiro.service.UserRoleService;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("House")
public class HouseIndexController {//住房模块
    @Autowired
    TypeInformationService typeInformationService;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    PayService payService;

    @GetMapping("index")
    public String ResulePage(){
        return "House_information/house_index";
    }

    @RequestMapping("List")
    @ResponseBody
    public PageUtil<TypeInformation> List(Integer limit, Integer page) {
        List<TypeInformation> sysRaiseList = typeInformationService.selectAll(limit, page);
        return new PageUtil<>(sysRaiseList, typeInformationService.count());
    }

    @RequestMapping("strList")
    @ResponseBody
    public PageUtil<TypeInformation> strList(Integer limit, Integer page) {
        List<TypeInformation> sysRaiseList = typeInformationService.selectAllStr(limit, page,"待出租");
        return new PageUtil<>(sysRaiseList, typeInformationService.count());
    }

    @GetMapping("rent")
    public String Rent(){
        return "House_information/house_rent";
    }

    @PostMapping("jion")
    @ResponseBody
    public Integer userjionHouse(Integer userRent,String userName,String userNumber) {

        User login = (User)SecurityUtils.getSubject().getPrincipal();//获取当前登录对象

        User Newuser = userService.findByLoginName(login.getLoginName());
        Newuser.setUserName(userName);
        Newuser.setUserNumber(userNumber);
        Newuser.setUserRent(userRent);
        Integer s = userService.updateByPrimaryKey(Newuser);
        if(s.equals(1)){

            TypeInformation NewHouseInformation = typeInformationService.selectByPrimaryKey(userRent);
            NewHouseInformation.setSell("已出租");
            NewHouseInformation.setEcut(1);
            NewHouseInformation.setGcut(1);
            NewHouseInformation.setWcut(1);
            NewHouseInformation.setInternet(1);
            Integer h = typeInformationService.updateByPrimaryKey(NewHouseInformation);
            if(h.equals(1)){

                UserRole userRole = userRoleService.selectById(Newuser.getUserId());
                userRole.setRoleId(2);
                Integer result = userRoleService.updateByPrimaryKey(userRole);

                return result;
            }
            else return 0;
        }
        else return 0;
    }

    @GetMapping("add")
    public String AddPage(){
        return "House_information/add";
    }

    @PostMapping("add")
    @ResponseBody
    public Integer AddHouse(TypeInformation typeInformation){
        List<TypeInformation> sele = typeInformationService.selectByRegion(typeInformation.getRegion());
        for(Integer i = 1; i<sele.size(); i++){
            if(sele.get(i).getNumber().equals(typeInformation.getNumber())){
                return 2;//返回2代表数据库中拥有此住房信息
            }
        }
        typeInformation.setGas(0f);
        typeInformation.setWater(0f);
        typeInformation.setElectricity(0f);
        typeInformation.setInternet(0);
        typeInformation.setEcut(0);
        typeInformation.setGcut(0);
        typeInformation.setWcut(0);
        typeInformation.setInternet(0);

        Integer result = typeInformationService.insert(typeInformation);
        return result;
    }

    @GetMapping("edit")
    public String EditPage(){
        return "House_information/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    public Integer EditHouse(TypeInformation typeInformation){
        TypeInformation selectHouse = typeInformationService.selectByPrimaryKey(typeInformation.getId());
        if(selectHouse.getSell().equals("已出租")){
            return 2;
        }else {
            selectHouse.setType(typeInformation.getType());//户型
            selectHouse.setArea(typeInformation.getArea());//面积
            selectHouse.setStructure(typeInformation.getStructure());//结构
            selectHouse.setSell(typeInformation.getSell());//状态
            selectHouse.setRent(typeInformation.getRent());//房租

            Integer result = typeInformationService.updateByPrimaryKey(selectHouse);
            return result;
        }
    }

    @PostMapping("delete")
    @ResponseBody
    public Integer deletePage(Integer id){
        TypeInformation sele = typeInformationService.selectByPrimaryKey(id);
        if(sele.getSell().equals("已出租")){
            return 2;
        }else {
            Integer result = typeInformationService.deleteByPrimaryKey(id);
            return result;
        }
    }

    @GetMapping("housing")
    public String Housing(){
        return "House_information/housing";
    }

    @PostMapping("housing")
    @ResponseBody
    public TypeInformation ResultHousing(){
        User login = (User)SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        TypeInformation result = typeInformationService.selectByPrimaryKey(login.getUserRent());
        return result;
    }
}
