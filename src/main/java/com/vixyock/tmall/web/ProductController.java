package com.vixyock.tmall.web;

import com.vixyock.tmall.dao.ProductDAO;
import com.vixyock.tmall.pojo.Category;
import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.service.ProductService;
import com.vixyock.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ProductController {
    @Autowired ProductService productService;

    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Page4Navigator<Product> page =productService.list(cid, start, size,5 );

        return page;
    }

    @GetMapping("products/{id}")
    public Product get(@PathVariable("id") int id){
        Product bean = productService.get(id);
        return bean;
    }

    @PutMapping("/products")
    public Object update(@RequestBody Product bean) throws Exception {
        productService.update(bean);
        return bean;
    }

    @PostMapping("/products")
    public Product add(@RequestBody Product bean){
        bean.setCreateDate(new Date());
        productService.add(bean);
        return bean;
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id){
        productService.delete(id);
        return null;
    }
}
