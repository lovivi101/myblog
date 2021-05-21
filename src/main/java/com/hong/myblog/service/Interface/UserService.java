package com.hong.myblog.service.Interface;

import com.hong.myblog.entity.User;

public interface UserService {

    //核对用户名和密码
    User CheckUser(String username , String password);
}
