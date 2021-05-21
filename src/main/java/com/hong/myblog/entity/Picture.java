package com.hong.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @description:
 * @author: hjx
 * @time: 2021年05月10日 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("picture")
public class Picture {
    private Long id;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;
}
