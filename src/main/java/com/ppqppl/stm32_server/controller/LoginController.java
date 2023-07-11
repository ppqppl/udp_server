package com.ppqppl.stm32_server.controller;

import com.ppqppl.stm32_server.entity.User;
import com.ppqppl.stm32_server.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {

    String loginmsg = "登录使用数据管理系统！";

    @Autowired
    LoginRepository loginRepository;

    List<User> users = null;

    @RequestMapping("/login")
    public String login(ModelMap map){
        users = loginRepository.findAll();
        map.addAttribute("newuser",new User());
        map.addAttribute("loginmsg",loginmsg);
        return "Login";
    }

    @RequestMapping("/dologin")
    public String dologin(User user,ModelMap map){
        List<User> myusers =  loginRepository.findByAccount(user.getAccount());
        if(myusers == null||myusers.size() == 0){
            loginmsg = "用户账号不存在！";
            map.addAttribute("loginmsg",loginmsg);
            return "redirect:/login";
        }
        else{
            myusers = null;
            myusers = loginRepository.findByAccountAndPwd(user.getAccount(),user.getPwd());
            if(myusers == null||myusers.size()==0){
                loginmsg = "用户密码错误！";
                map.addAttribute("loginmsg",loginmsg);
                return "redirect:/login";
            }
            else{
                loginmsg = "登录使用数据管理系统！";
                map.addAttribute("loginmsg",loginmsg);
                return "redirect:/index";
            }
        }
    }

}
