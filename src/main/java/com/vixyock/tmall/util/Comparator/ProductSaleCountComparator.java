package com.vixyock.tmall.util.Comparator;

import com.vixyock.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author ：VIxyock
 * @description：销量比较器
 */

public class ProductSaleCountComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()-p1.getSaleCount();
    }

}