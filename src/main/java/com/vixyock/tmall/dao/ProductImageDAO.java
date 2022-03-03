package com.vixyock.tmall.dao;

import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDAO extends JpaRepository<ProductImage,Integer> {
    List<ProductImage> findByProductAndTypeOrderByIdAsc(Product product,String type);
}
