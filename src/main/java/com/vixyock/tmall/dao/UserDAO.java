package com.vixyock.tmall.dao;

import com.vixyock.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByName(String name);
}
