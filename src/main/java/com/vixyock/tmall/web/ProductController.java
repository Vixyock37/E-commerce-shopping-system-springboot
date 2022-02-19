package com.vixyock.tmall.web;

import com.vixyock.tmall.dao.ProductDAO;
import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.service.ProductService;
import com.vixyock.tmall.util.Page4Navigator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService;

    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable int cid, @RequestParam(name = "start", defaultValue = "0") int start, @RequestParam(name = "size", defaultValue = "5") int size) {
        Page4Navigator<Product> page = productService.list(cid, start, size, 5);
        return page;
    }
}
