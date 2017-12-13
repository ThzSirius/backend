package com.thzSirius.service;

import com.thzSirius.bean.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by THZ on 2017/12/12.
 */
public interface UserService {
   public User getUser(int id);
}
