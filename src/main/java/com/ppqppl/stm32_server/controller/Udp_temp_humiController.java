package com.ppqppl.stm32_server.controller;

import com.ppqppl.stm32_server.entity.UDP;
import com.ppqppl.stm32_server.entity.Udp_temp_humi;
import com.ppqppl.stm32_server.repository.UDPRepository;
import com.ppqppl.stm32_server.repository.Udp_temp_humiRepository;
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
public class Udp_temp_humiController {

    @Autowired
    private Udp_temp_humiRepository udp_temp_humiRepository;

    Page<Udp_temp_humi> all = null;

    @RequestMapping("/udp2")
    public String udp_temp_humis(ModelMap map){
        if(all == null){
            all = udp_temp_humiRepository.findAll(PageRequest.of(0,10));
        }
        int jump = all.getNumber();
        map.addAttribute("udp2datainfo",all);
        map.addAttribute("jumppage",jump);
        return "UDP2data";
    }

    @RequestMapping("/udp2show")
    public String index(@RequestParam(value="pagenum",defaultValue="0")int pagenum,
                        @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map)
    {
        all = udp_temp_humiRepository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("udp2datainfo",all);
        return "redirect:/udp2";
    }

    @PostMapping("/udp2jump")
    public String jump(@RequestParam(value="jump",defaultValue="0")int pagenum,
                       @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map){
        pagenum -= 1;
        if(pagenum<0){
            pagenum = 0;
        }
        if(pagenum>all.getTotalPages()){
            pagenum = all.getTotalPages()-1;
        }
        all = udp_temp_humiRepository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("udp2datainfo",all);
        return "redirect:/udp2";
    }

    @RequestMapping("/udp2data")
    public List<Udp_temp_humi> udp_temp_humi(){
        return udp_temp_humiRepository.findAll();
    }
}
