package com.hong.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 分类实体类
 * @author: hjx
 * @time: 2021年05月10日 17:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("type")
public class Type {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

}
