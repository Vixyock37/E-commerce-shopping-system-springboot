package com.vixyock.tmall.service;

import com.vixyock.tmall.dao.ProductImageDAO;
import com.vixyock.tmall.pojo.OrderItem;
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

    public ProductImage get(int id) {
        return productImageDAO.findOne(id);
    }

    public void delete(int id) {
        productImageDAO.delete(id);
    }

    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if(!singleImages.isEmpty()) {
            product.setFirstProductImage(singleImages.get(0));
//            System.out.println(singleImages.get(0).getId());
        }
        else
            product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }

    public void setFirstProductImages(List<Product> products){
        for(Product product:products)
            setFirstProductImage(product);
    }

    public void setFirstProductImagesOnOrderItems(List<OrderItem> ois) {
        for (OrderItem orderItem : ois) {
            setFirstProductImage(orderItem.getProduct());
        }
    }

}
