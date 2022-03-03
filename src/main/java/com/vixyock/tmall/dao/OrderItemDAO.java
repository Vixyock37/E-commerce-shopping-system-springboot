package com.vixyock.tmall.dao;

import com.vixyock.tmall.pojo.Order;
import com.vixyock.tmall.pojo.OrderItem;
import com.vixyock.tmall.pojo.Product;
import com.vixyock.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdAsc(Order order);
    List<OrderItem> findByProduct(Product product);
    List<OrderItem> findByUserAndOrderIsNull(User user);
}
