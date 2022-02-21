package com.vixyock.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminPageController {
    //分类管理Category
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
    //产品管理Product
    @GetMapping(value = "admin_product_list")
    public String listProduct(){ return "admin/listProduct"; }
    @GetMapping(value = "/admin_product_edit")
    public String editProduct(){ return "admin/editProduct"; }
    //产品图片管理ProductImage
    @GetMapping(value = "admin_productImage_list")
    public String listProductImage(){return "admin/listProductImage";}
}
