package com.vixyock.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：VIxyock
 * @description：做前台页面的跳转
 */

@Controller
public class ForePageController {
    @GetMapping(value="/")
    public String index(){
        return "redirect:home";
    }
    @GetMapping(value="/home")
    public String home(){
        return "fore/home";
    }
}