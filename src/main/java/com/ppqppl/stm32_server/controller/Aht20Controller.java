package com.ppqppl.stm32_server.controller;

import com.ppqppl.stm32_server.entity.Aht20;
import com.ppqppl.stm32_server.entity.TCP;
import com.ppqppl.stm32_server.repository.Aht20Repository;
import com.ppqppl.stm32_server.utils.PageSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Aht20Controller {

    PageSelect page = new PageSelect();

    @Autowired
    private Aht20Repository aht20Repository;

    Page<Aht20> all = null;

    @RequestMapping("/aht20")
    public String aht(ModelMap map){
        if(all == null){
            all = aht20Repository.findAll(PageRequest.of(0,10));
        }
        int jump = all.getNumber();
        map.addAttribute("aht20datainfo",all);
        map.addAttribute("jumppage",jump);
        return "Aht20data";
    }

    @RequestMapping("/aht20show")
    public String index(@RequestParam(value="pagenum",defaultValue="0")int pagenum,
                        @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map)
    {
        all = aht20Repository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("aht20datainfo",all);
//        map.addAttribute("pagenum",all.getNumber());
//        map.addAttribute("pagesize",all.getNumberOfElements());
//        map.addAttribute("pagetotal",all.getTotalPages());
//        map.addAttribute("pagesizetotal",all.getTotalElements());
        return "redirect:/aht20";
    }

    @PostMapping("/aht20jump")
    public String jump(@RequestParam(value="jump",defaultValue="0")int pagenum,
                       @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map){
        pagenum -= 1;
        if(pagenum<0){
            pagenum = 0;
        }
        if(pagenum>all.getTotalPages()){
            pagenum = all.getTotalPages()-1;
        }
        all = aht20Repository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("aht20datainfo",all);
        return "redirect:/aht20";
    }

    @RequestMapping("/aht20data")
    public List<Aht20> aht20(){
        return aht20Repository.findAll();
    }

}
