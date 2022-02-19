package com.vixyock.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPageController {
    //分类管理
    @GetMapping(value = "/admin")
    public String admin(){
        return "redirect:admin_category_list";
    }
    @GetMapping(value = "/admin_category_list")
    public String listCategory(){
        return "admin/listCategory";
    }
    @GetMapping(value = "/admin_category_edit")
    public String editCategory(){ return "admin/editCategory"; }
    //用户管理
    @GetMapping(value = "admin_user_list")
    public String listUser(){return "admin/listUser";}
}
