package com.vixyock.tmall.dao;

import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.Property;
import com.vixyock.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer> {
    List<PropertyValue> findByProductOrderByIdAsc(Product product);
    PropertyValue getByPropertyAndProduct(Property property, Product product);
}
