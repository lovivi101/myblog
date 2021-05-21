package com.hong.myblog.dao;

import com.hong.myblog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: hjx
 * @time: 2021年05月10日 17:04
 */

@Mapper
@Repository
public interface UserDao {

    /*查询用户名和密码*/
    @Select("select * from myblog.user where username = #{username} and password = #{password}")
    User FindUser(@Param("username") String username , @Param("password") String password);


}
