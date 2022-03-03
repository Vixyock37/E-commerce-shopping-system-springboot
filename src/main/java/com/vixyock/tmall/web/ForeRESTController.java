package com.vixyock.tmall.web;

import com.vixyock.tmall.pojo.*;
import com.vixyock.tmall.service.*;
import com.vixyock.tmall.util.Comparator.*;
import com.vixyock.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

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

    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String name =  userParam.getName();
        name = HtmlUtils.htmlEscape(name);

        User user =userService.get(name,userParam.getPassword());
        if(null==user){
            String message ="账号密码错误";
            return Result.fail(message);
        }
        else{
            session.setAttribute("user", user);
            return Result.success();
        }
    }

    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) {
        Product product = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);

        List<PropertyValue> pvs = propertyValueService.list(product);
        List<Review> reviews = reviewService.list(product);
        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProductImage(product);

        Map<String,Object> map= new HashMap<>();
        map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);

        return Result.success(map);
    }

    @GetMapping("forecheckLogin")
    public Object checkLogin( HttpSession session) {
        User user =(User)  session.getAttribute("user");
        if(null!=user)
            return Result.success();
        return Result.fail("未登录");
    }

    @GetMapping("forecategory/{cid}")
    public Object category(@PathVariable int cid,String sort){
        Category c = categoryService.get(cid);
        productService.fill(c);
        productService.setSaleAndReviewNumber(c.getProducts());
        categoryService.removeCategoryFromProduct(c);

        if(null!=sort){
            switch(sort){
                case "review":
                    Collections.sort(c.getProducts(),new ProductReviewComparator());
                    break;
                case "date" :
                    Collections.sort(c.getProducts(),new ProductDateComparator());
                    break;

                case "saleCount" :
                    Collections.sort(c.getProducts(),new ProductSaleCountComparator());
                    break;

                case "price":
                    Collections.sort(c.getProducts(),new ProductPriceComparator());
                    break;

                case "all":
                    Collections.sort(c.getProducts(),new ProductAllComparator());
                    break;
            }
        }

        return c;
    }

    @PostMapping("foresearch")
    public Object search( String keyword){
        if(null==keyword)
            keyword = "";
        List<Product> ps= productService.search(keyword,0,20);
        productImageService.setFirstProductImages(ps);
        productService.setSaleAndReviewNumber(ps);
        return ps;
    }

}
