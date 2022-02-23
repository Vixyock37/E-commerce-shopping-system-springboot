package com.vixyock.tmall.service;


import com.vixyock.tmall.dao.PropertyValueDAO;
import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.Property;
import com.vixyock.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueService {

    @Autowired
    PropertyValueDAO propertyValueDAO;
    @Autowired
    PropertyService propertyService;

    public void update(PropertyValue bean) {
        propertyValueDAO.save(bean);
    }

    //初始化属性值，通过初始化来进行自动地增加，以便于后面的修改
    public void init(Product product) {
        List<Property> properties = propertyService.listByCategory(product.getCategory());
        for (Property property : properties) {
            PropertyValue propertyValue = getByPropertyAndProduct(product, property);
            if (null == propertyValue) {
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueDAO.save(propertyValue);
            }
        }
    }

    public PropertyValue getByPropertyAndProduct(Product product, Property property) {
        return propertyValueDAO.getByPropertyAndProduct(property, product);
    }

    public List<PropertyValue> list(Product product) {
        return propertyValueDAO.findByProductOrderByIdAsc(product);
    }
}