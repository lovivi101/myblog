package com.hong.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: hjx
 * @time: 2021年05月10日 16:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("blog")
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;

    private Integer commentCount;

    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    private Long typeId;
    private Long userId;
    private String description;
    private Type type;
    private User user;
    private List<Comment> comments = new ArrayList<>();
}
