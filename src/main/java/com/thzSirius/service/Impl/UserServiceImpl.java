package com.thzSirius.service.Impl;

import com.thzSirius.bean.User;
import com.thzSirius.dao.UserMapper;
import com.thzSirius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by THZ on 2017/12/13.
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    public User getUser(int id) {
        return userMapper.getUserByID(id);
    }
}
