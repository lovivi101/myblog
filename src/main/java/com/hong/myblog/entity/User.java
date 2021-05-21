package com.hong.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @description:
 * @author: hjx
 * @time: 2021年05月10日 16:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("user")
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}
