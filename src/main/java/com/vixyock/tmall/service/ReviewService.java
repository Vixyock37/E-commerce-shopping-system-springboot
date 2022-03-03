package com.vixyock.tmall.service;

import com.vixyock.tmall.dao.ReviewDAO;
import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired ReviewDAO reviewDAO;
    @Autowired ProductService productService;

    public void add(Review review) {
        reviewDAO.save(review);
    }

    public List<Review> list(Product product){
        List<Review> result =  reviewDAO.findByProductOrderByIdAsc(product);
        return result;
    }

    public int getCount(Product product) {
        return reviewDAO.countByProduct(product);
    }
}
