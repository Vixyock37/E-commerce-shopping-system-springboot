package com.vixyock.tmall.service;

import com.vixyock.tmall.dao.ProductImageDAO;
import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Autowired ProductImageDAO productImageDAO;
    @Autowired ProductService productService;

    public List<ProductImage> listSingleProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdAsc(product,type_single);
    }

    public List<ProductImage> listDetailProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdAsc(product,type_detail);
    }

    public void add(ProductImage bean) {
        productImageDAO.save(bean);
    }
}
