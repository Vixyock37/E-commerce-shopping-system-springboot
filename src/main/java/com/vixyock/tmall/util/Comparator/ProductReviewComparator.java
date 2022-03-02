package com.vixyock.tmall.util.Comparator;

import com.vixyock.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author ：VIxyock
 * @description：评价数比较器
 */

public class ProductReviewComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()-p1.getReviewCount();
    }

}
