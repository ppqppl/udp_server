package com.ppqppl.stm32_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("")
    public String showindex(ModelMap map){
        return "redirect:/login";
    }

    @RequestMapping("/index")
    public String showmain(ModelMap map){
        return "index";
    }

}
