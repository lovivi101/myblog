package com.hong.myblog.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
* @Description: FirstPageBlog
* @Author: hjx
* @Date: 2021/5/17
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Alias("FirstPageBlog")
public class FirstPageBlog {

    //Blog
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;

}