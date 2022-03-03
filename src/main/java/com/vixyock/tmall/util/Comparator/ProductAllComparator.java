package com.vixyock.tmall.util.Comparator;

import com.vixyock.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author ：VIxyock
 * @description：综合比较器，把销量*评价高的放在前面
 */

public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount();
    }
}
