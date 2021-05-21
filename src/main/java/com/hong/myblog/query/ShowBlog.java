package com.hong.myblog.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Description: 编辑修改文章实体类
 * @Author: ONESTAR
 * @Date: Created in 23:41 2020/4/1
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */
/**
* @Description:  编辑修改文章实体类
* @Author: hjx
* @Date: 2021/5/17
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowBlog {

    private Long id;
    private String flag;
    private String title;
    private String content;
    private Long typeId;
    private String firstPicture;
    private String description;
    private boolean recommend;
    private boolean published;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;

}