package com.vixyock.tmall.web;

import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.ProductImage;
import com.vixyock.tmall.service.CategoryService;
import com.vixyock.tmall.service.ProductImageService;
import com.vixyock.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductImageController {
    @Autowired ProductService productService;
    @Autowired ProductImageService productImageService;
    @Autowired CategoryService categoryService;

    @GetMapping("/products/{pid}/productImages")
    public List<ProductImage> list(@RequestParam("type") String type, @PathVariable("pid") int pid){
        Product product = productService.get(pid);
        System.out.println(product.toString());
        if(ProductImageService.type_single.equals(type)){
            List<ProductImage> singles =  productImageService.listSingleProductImages(product);
            return singles;
        }
        else if(ProductImageService.type_detail.equals(type)){
            List<ProductImage> details =  productImageService.listDetailProductImages(product);
            return details;
        }
        else
            return new ArrayList<>();
    }
}
