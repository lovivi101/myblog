package com.hong.myblog.query;

import com.hong.myblog.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
* @Description: BlogQuery
* @Author: hjx
* @Date: 2021/5/17
*/
@Alias("BlogQuery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {

    private Long id;
    private String title;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;

}