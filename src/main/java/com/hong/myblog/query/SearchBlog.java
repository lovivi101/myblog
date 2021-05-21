package com.hong.myblog.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;


/**
* @Description:  搜索博客管理列表
* @Author: hjx
* @Date: 2021/5/17
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("SearchBlog")
public class SearchBlog {

    private String title;
    private Long typeId;

}