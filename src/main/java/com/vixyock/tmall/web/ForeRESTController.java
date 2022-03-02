package com.vixyock.tmall.web;

import com.vixyock.tmall.pojo.Category;
import com.vixyock.tmall.pojo.User;
import com.vixyock.tmall.service.CategoryService;
import com.vixyock.tmall.service.ProductService;
import com.vixyock.tmall.service.UserService;
import com.vixyock.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


/**
 * @author ：VIxyock
 * @description：用以对应前台的路径
 */

@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/forehome")
    public Object home() {
        List<Category> cs = categoryService.list();     //  1. 查询所有分类
        productService.fill(cs);                        //  2. 为这些分类填充产品集合
        productService.fillByRow(cs);                   //  3. 为这些分类填充推荐产品集合
        categoryService.removeCategoryFromProduct(cs);  //  4. 移除产品里的分类信息，以免出现重复递归
        return cs;
    }
    @PostMapping("/foreregister")
    public Object register(@RequestBody User user){
        String name = user.getName();
        String password = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);
        if(exist){
            return Result.fail("用户名已经被使用，不能使用");
        }
        user.setPassword(password);
        userService.add(user);
        return Result.success();
    }
}
