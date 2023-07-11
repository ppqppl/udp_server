package com.ppqppl.stm32_server.controller;

import com.ppqppl.stm32_server.entity.User;
import com.ppqppl.stm32_server.repository.LoginRepository;
import com.ppqppl.stm32_server.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RegisterController {

    String regmsg = "获取你的免费账号！";

    @Autowired
    RegisterRepository registerRepository;

    List<User> users = null;

    @RequestMapping("/register")
    public String showregister(ModelMap map){
        map.addAttribute("regmsg",regmsg);
        map.addAttribute("newuser",new User());
        return "register";
    }

    @RequestMapping("/doregister")
    public String doregister(User user, ModelMap map){
        List<User> myusers =  registerRepository.findByAccount(user.getAccount());
        if(myusers!=null&&myusers.size()!=0){
            regmsg = "账号已存在！";
            map.addAttribute("regmsg",regmsg);
            return "redirect:/register";
        }
        else{
            myusers = null;
            myusers = registerRepository.findByMail(user.getMail());
            if(myusers != null&&myusers.size()!=0){
                regmsg = "邮箱已经被注册！";
                map.addAttribute("regmsg",regmsg);
                return "redirect:/register";
            }
            else{
                registerRepository.save(user);
                regmsg = "获取你的免费账号！";
                map.addAttribute("regmsg",regmsg);
                return "redirect:/login";
            }
        }
    }
}
