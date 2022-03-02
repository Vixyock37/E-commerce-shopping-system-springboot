package com.vixyock.tmall.util.Comparator;

import com.vixyock.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author ：VIxyock
 * @description：价格比较器
 */

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice()-p2.getPromotePrice());
    }

}
