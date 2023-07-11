package com.ppqppl.stm32_server.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppqppl.stm32_server.entity.TCP;
import com.ppqppl.stm32_server.repository.TCPRepository;
import com.ppqppl.stm32_server.utils.PageSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
public class TCPController {

    PageSelect page = new PageSelect();

    @Autowired
    private TCPRepository tcpRepository;
    Page<TCP> all = null;
    @RequestMapping("/tcp")
    public String tcp(ModelMap map){
        if(all == null){
            all = tcpRepository.findAll(PageRequest.of(0,10));
        }
        int jump = all.getNumber();
        map.addAttribute("tcpdatainfo",all);
        map.addAttribute("jumppage",jump);
        return "TCPdata";
    }

    @RequestMapping("/tcpshow")
    public String index(@RequestParam(value="pagenum",defaultValue="0")int pagenum,
                        @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map)
    {
        all = tcpRepository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("tcpdatainfo",all);
        return "redirect:/tcp";
    }

    @PostMapping("/tcpjump")
    public String jump(@RequestParam(value="jump",defaultValue="0")int pagenum,
                       @RequestParam(value="pagesize",defaultValue="10")int pagesize, ModelMap map){
        pagenum -= 1;
        if(pagenum<0){
            pagenum = 0;
        }
        if(pagenum>all.getTotalPages()){
            pagenum = all.getTotalPages()-1;
        }
        all = tcpRepository.findAll(PageRequest.of(pagenum,pagesize));
        map.addAttribute("tcpdatainfo",all);
        return "redirect:/tcp";
    }

    @RequestMapping("/TCPdata")
    public List<TCP> TCP(){
        return tcpRepository.findAll();
    }



}
