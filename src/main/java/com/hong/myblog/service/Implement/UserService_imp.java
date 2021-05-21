package com.hong.myblog.service.Implement;

import com.hong.myblog.dao.UserDao;
import com.hong.myblog.entity.User;
import com.hong.myblog.service.Interface.UserService;
import com.hong.myblog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户业务层接口实现类
 * @author: hjx
 * @time: 2021年05月10日 17:17
 */
@Service
public class UserService_imp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User CheckUser(String username, String password) {
        User user = userDao.FindUser(username, MD5Util.code(password));
        return user;
    }
}
