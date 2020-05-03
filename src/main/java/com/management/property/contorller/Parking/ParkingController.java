package com.management.property.contorller.Parking;

import com.management.property.Service.ParkingPlantService;
import com.management.property.pojo.ParkingPlant;
import com.management.property.pojo.Pay;
import com.management.property.pojo.TypeInformation;
import com.management.property.shiro.PageUtil;
import com.management.property.shiro.pojo.User;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("Parking")
public class ParkingController {//住房出租
    @Autowired
    ParkingPlantService parkingPlantService;
    @Autowired
    UserService userService;

    @GetMapping("index")
    public String IndexPage(){
        return "ParkingPlant/parking_index";
    }

    @GetMapping("add")
    public String Add(){
        return "ParkingPlant/add";
    }

    @GetMapping("edit")
    public String Edit(){ return "ParkingPlant/edit"; }

    @GetMapping("user")
    public String User(){
        return "ParkingPlant/parking_user";
    }

    @GetMapping("look")
    public String Look(){
        return "ParkingPlant/look";
    }

    @RequestMapping("comList")
    @ResponseBody
    public PageUtil<ParkingPlant> ComList(Integer limit, Integer page) {
        List<ParkingPlant> sysRaiseList = parkingPlantService.selectAllcom(limit, page ,"空闲");
        return new PageUtil<>(sysRaiseList, parkingPlantService.count());
    }

    @RequestMapping("List")
    @ResponseBody
    public PageUtil<ParkingPlant> List(Integer limit, Integer page) {
        List<ParkingPlant> sysRaiseList = parkingPlantService.selectAll(limit, page);
        return new PageUtil<>(sysRaiseList, parkingPlantService.count());
    }

    @PostMapping("lease")
    @ResponseBody
    public Integer Lease(Integer id) {
        User login = (User)SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        User user = userService.selectByPrimaryKey(login.getUserId());
        if(!user.getParkingId().equals("")){
            return 2;
        }
        else {
            user.setParkingId(id);
            Integer i = userService.updateByPrimaryKey(user);
            ParkingPlant parkingPlant = parkingPlantService.selectByPrimaryKey(id);
            parkingPlant.setStatus("已出租");
            Integer j = parkingPlantService.updateByPrimaryKey(parkingPlant);
            if (i==1&&j==1){
                return 1;
            }
            else return 0;
        }
    }

    @RequestMapping("userPark")
    @ResponseBody
    public PageUtil<ParkingPlant> UerPark(Integer limit, Integer page) {
        User login = (User)SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        User user = userService.selectByPrimaryKey(login.getUserId());
        List<ParkingPlant> sysRaiseList = parkingPlantService.selectAllByParking(limit, page, user.getParkingId());
        return new PageUtil<>(sysRaiseList, parkingPlantService.count());
    }

    @PostMapping("delete")
    @ResponseBody
    public Integer deletePage(Integer id){
        ParkingPlant sele = parkingPlantService.selectByPrimaryKey(id);
        if(sele.getStatus().equals("已出租")){
            return 2;
        }else {
            Integer result = parkingPlantService.deleteByPrimaryKey(id);
            return result;
        }
    }

    @PostMapping("edit")
    @ResponseBody
    public Integer editPage(ParkingPlant parkingPlant){
        ParkingPlant sele = parkingPlantService.selectByPrimaryKey(parkingPlant.getId());
        if(sele.getStatus().equals("已出租")){
            return 2;
        }else {
            sele.setStatus(parkingPlant.getStatus());
            sele.setCost(parkingPlant.getCost());
            sele.setType(parkingPlant.getType());

            Integer result = parkingPlantService.updateByPrimaryKey(sele);
            return result;
        }
    }

    @PostMapping("add")
    @ResponseBody
    public Integer Toadd(ParkingPlant parkingPlant){
        List<ParkingPlant> sele = parkingPlantService.selectByRegion(parkingPlant.getParkingPosition());
        for(int i = 1; i<sele.size(); i++){
            if(sele.get(i).getNumber().equals(parkingPlant.getNumber())){
                return 2;//返回2代表数据库中拥有此住房信息
            }
        }
        parkingPlant.setType(parkingPlant.getType());
        parkingPlant.setCost(parkingPlant.getCost());
        parkingPlant.setStatus(parkingPlant.getStatus());
        parkingPlant.setNumber(parkingPlant.getNumber());
        parkingPlant.setParkingPosition(parkingPlant.getParkingPosition());

        Integer result = parkingPlantService.insert(parkingPlant);
        return result;
    }

    @PostMapping("exit")
    @ResponseBody
    public Integer Exit(Integer id){
        User login = (User)SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        User user = userService.selectByPrimaryKey(login.getUserId());
        user.setParkingId(0);
        userService.updateByPrimaryKey(user);
        ParkingPlant sele = parkingPlantService.selectByPrimaryKey(id);
        sele.setStatus("空闲");
        Integer result = parkingPlantService.updateByPrimaryKey(sele);
        return result;
    }
}
