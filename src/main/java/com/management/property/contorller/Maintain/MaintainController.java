package com.management.property.contorller.Maintain;

import com.management.property.Service.RepairAnnalService;
import com.management.property.Service.RepairManService;
import com.management.property.Service.TypeInformationService;
import com.management.property.pojo.RepairAnnal;
import com.management.property.pojo.RepairMan;
import com.management.property.pojo.TypeInformation;
import com.management.property.shiro.PageUtil;
import com.management.property.shiro.pojo.User;
import com.management.property.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("Maintain")
public class MaintainController {//维修
    @Autowired
    RepairAnnalService repairAnnalService;
    @Autowired
    RepairManService repairManService;
    @Autowired
    UserService userService;
    @Autowired
    TypeInformationService typeInformationService;

    @GetMapping("index")
    public String IndexPage(){
        return "Maintain/Maintain_index";
    }

    @GetMapping("add")
    public String Add(){
        return "Maintain/add";
    }

    @GetMapping("edit")
    public String Edit(){ return "Maintain/send"; }

    @GetMapping("select")
    public String Select(){ return "Maintain/select"; }

    @PostMapping("delete")
    @ResponseBody
    public Integer Delete(Integer id){
        RepairAnnal Annal = repairAnnalService.selectByPrimaryKey(id);
        if(Annal.getRepairStatus().equals("维修中")){
            return 2;
        }else if(Annal.getRepairStatus().equals("已修理")){
            return 3;
        }else {
            Integer result = repairAnnalService.deleteByPrimaryKey(id);
            return result;
        }
    }

    @RequestMapping("List")
    @ResponseBody
    public PageUtil<RepairAnnal> List(Integer limit, Integer page) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        List<RepairAnnal> sysRaiseList = repairAnnalService.selectAllByUserId(limit, page, login.getUserId());
        return new PageUtil<>(sysRaiseList, repairAnnalService.count());
    }

    @RequestMapping("AllList")
    @ResponseBody
    public PageUtil<RepairAnnal> AllList(Integer limit, Integer page) {
        List<RepairAnnal> sysRaiseList = repairAnnalService.selectAll(limit, page);
        return new PageUtil<>(sysRaiseList, repairAnnalService.count());
    }

    @PostMapping("examine")
    @ResponseBody
    public RepairAnnal Examine(Integer id) {
        RepairAnnal sysRaiseList = repairAnnalService.selectByPrimaryKey(id);
        return sysRaiseList;
    }

    @PostMapping("add")
    @ResponseBody
    public Integer ToAdd(RepairAnnal repairAnnal) {
        User login = (User)SecurityUtils.getSubject().getPrincipal();//获取当前登录对象

        Date time = new Date();//获取创建时间
        Timestamp timestamp = new Timestamp(time.getTime());

        repairAnnal.setUserName(login.getUserName());
        repairAnnal.setUserId(login.getUserId());
        repairAnnal.setRequestDate(timestamp);
        repairAnnal.setRepairStatus("未修理");

        Integer result = repairAnnalService.insert(repairAnnal);
        return result;
    }

    @RequestMapping("Man")//-----------------------------------------------------------------------------------------↓重写
    @ResponseBody
    public PageUtil<RepairMan> ManList(Integer limit, Integer page) {
        List<RepairMan> sysRaiseList = repairManService.selectAllByStatus(limit,page,"空闲");
        return new PageUtil<>(sysRaiseList, repairManService.count());
    }

    @PostMapping("Send")
    @ResponseBody
    public Integer Send(Integer id,Integer uid) {//派遣
        RepairAnnal repairAnnal = repairAnnalService.selectByPrimaryKey(uid);
        RepairMan repairMan = repairManService.selectByPrimaryKey(id);

            repairAnnal.setRepairName(repairMan.getRepairName());
            repairAnnal.setRepairPhone(repairMan.getRepairPhone());
            repairAnnal.setRepairId(repairMan.getId());
            repairAnnal.setRepairStatus("维修中");
            repairAnnalService.updateByPrimaryKey(repairAnnal);

            repairMan.setStatus("派遣中");
            Integer result = repairManService.updateByPrimaryKey(repairMan);
            return result;
    }

    @PostMapping("return")
    @ResponseBody
    public Integer Return(Integer id) {//召回
        RepairAnnal annal = repairAnnalService.selectByPrimaryKey(id);

            Date time = new Date();//获取创建时间
            Timestamp timestamp = new Timestamp(time.getTime());

            annal.setRepairDate(timestamp);
            annal.setRepairStatus("已修理");
            repairAnnalService.updateByPrimaryKey(annal);

            RepairMan sysRaiseList = repairManService.selectByPrimaryKey(annal.getRepairId());
            sysRaiseList.setStatus("空闲");
            Integer result = repairManService.updateByPrimaryKey(sysRaiseList);
            return result;
    }

    @PostMapping("resultInformation")
    @ResponseBody
    public TypeInformation ResultInformation() {
        User login = (User)SecurityUtils.getSubject().getPrincipal();//获取当前登录对象
        User user = userService.selectByPrimaryKey(login.getUserId());//当前用户信息

        TypeInformation typeInformation = typeInformationService.selectByPrimaryKey(user.getUserRent());

        return typeInformation;
    }

}
