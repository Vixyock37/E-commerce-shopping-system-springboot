package com.vixyock.tmall.dao;

import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review,Integer> {
    List<Review> findByProductOrderByIdAsc(Product product);
    int countByProduct(Product product);
}
