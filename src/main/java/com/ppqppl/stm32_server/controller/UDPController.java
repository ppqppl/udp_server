package com.ppqppl.stm32_server.controller;

import com.ppqppl.stm32_server.entity.Aht20;
import com.ppqppl.stm32_server.entity.UDP;
import com.ppqppl.stm32_server.repository.Aht20Repository;
import com.ppqppl.stm32_server.repository.UDPRepository;
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
public class UDPController {

    @Autowired
    private UDPRepository udpRepository;

    Page<UDP> all = null;

    @RequestMapping("/udp")
    public String udps(ModelMap map){
        if(all == null){
            all = udpRepository.findAll(PageRequest.of(0,10));
        }
        int jump = all.getNumber();
        map.addAttribute("udpdatainfo",all);
        map.addAttribute("jumppage",jump);
        return "UDPdata";
    }

    @RequestMapping("/udpshow")
    public String index(@RequestParam(value="pagenum",defaultValue="0")int pagenum,
                        @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map)
    {
        all = udpRepository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("udpdatainfo",all);
        return "redirect:/udp";
    }

    @PostMapping("/udpjump")
    public String jump(@RequestParam(value="jump",defaultValue="0")int pagenum,
                       @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map){
        pagenum -= 1;
        if(pagenum<0){
            pagenum = 0;
        }
        if(pagenum>all.getTotalPages()){
            pagenum = all.getTotalPages()-1;
        }
        all = udpRepository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("udpdatainfo",all);
        return "redirect:/udp";
    }

    @RequestMapping("/udpdata")
    public List<UDP> udp(){
        return udpRepository.findAll();
    }
}
