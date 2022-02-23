package com.vixyock.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPageController {
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
    //分类属性管理Property
    @GetMapping(value = "/admin_property_list")
    public String listProperty(){return "admin/listProperty";}
    @GetMapping(value = "/admin_property_edit")
    public String editProperty(){return "admin/editProperty";}

    //产品管理Product
    @GetMapping(value = "admin_product_list")
    public String listProduct(){ return "admin/listProduct"; }
    @GetMapping(value = "/admin_product_edit")
    public String editProduct(){ return "admin/editProduct"; }
    //产品图片管理ProductImage
    @GetMapping(value = "admin_productImage_list")
    public String listProductImage(){return "admin/listProductImage";}
    //用户管理
    @GetMapping(value = "admin_user_list")
    public String listUser(){return "admin/listUser";}
}
