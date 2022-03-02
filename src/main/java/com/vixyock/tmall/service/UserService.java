package com.vixyock.tmall.service;

import com.vixyock.tmall.dao.UserDAO;
import com.vixyock.tmall.pojo.User;
import com.vixyock.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String name) {
        User user = getByName(name);
        return null!=user;
    }

    private User getByName(String name) {
        return userDAO.findByName(name);
    }

    public Page4Navigator<User> list(int start, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
