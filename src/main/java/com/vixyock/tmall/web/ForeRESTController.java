package com.vixyock.tmall.web;

import com.vixyock.tmall.pojo.Category;
import com.vixyock.tmall.service.CategoryService;
import com.vixyock.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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





    @GetMapping("/forehome")
    public Object home() {
        List<Category> cs = categoryService.list();     //  1. 查询所有分类
        productService.fill(cs);                        //  2. 为这些分类填充产品集合
        productService.fillByRow(cs);                   //  3. 为这些分类填充推荐产品集合
        categoryService.removeCategoryFromProduct(cs);  //  4. 移除产品里的分类信息，以免出现重复递归
        return cs;
    }
}
