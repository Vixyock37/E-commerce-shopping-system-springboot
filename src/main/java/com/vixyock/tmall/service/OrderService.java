package com.vixyock.tmall.service;

import com.vixyock.tmall.dao.OrderDAO;
import com.vixyock.tmall.pojo.Order;
import com.vixyock.tmall.pojo.OrderItem;
import com.vixyock.tmall.util.Page4Navigator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：VIxyock
 * @description：提供分页查询。还提供了订单状态的常量
 */

@Service
public class OrderService {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    @Autowired
    OrderDAO orderDAO;

    public Page4Navigator<Order> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = orderDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    //因为SpringMVC ( springboot 里内置的mvc框架是 这个东西)的 RESTFUL 注解，
    //在把一个Order转换为json的同时，会把其对应的 orderItems 转换为 json数组，
    //而 orderItem对象上有 order属性,为避免无穷递归写此函数
    public void removeOrderFromOrderItem(List<Order> orders) {
        for (Order order : orders) {
            removeOrderFromOrderItem(order);
        }
    }

    private void removeOrderFromOrderItem(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(null);
        }
    }

    public Order get(int oid) {
        return orderDAO.findOne(oid);
    }

    public void update(Order bean) {
        orderDAO.save(bean);
    }

}