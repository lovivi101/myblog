package com.hong.myblog.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
* @Description:  RecommendBlog
* @Author: hjx
* @Date: 2021/5/17
*/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("RecommendBlog")
public class RecommendBlog {

    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend;


}