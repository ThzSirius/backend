package com.thzSirius.dao;

import com.thzSirius.bean.User;
import org.springframework.stereotype.Repository;

/**
 * Created by THZ on 2017/12/12.
 */

public interface UserMapper {
    User getUserByID(int id);
}
