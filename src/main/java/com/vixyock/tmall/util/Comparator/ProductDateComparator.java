package com.vixyock.tmall.util.Comparator;

/**
 * @author ：VIxyock
 * @description：日期比较器
 */

import com.vixyock.tmall.pojo.Product;

import java.util.Comparator;



public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }
}
