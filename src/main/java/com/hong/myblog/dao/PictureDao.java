package com.hong.myblog.dao;


import com.hong.myblog.entity.Picture;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureDao {


    //查询照片
    @Select("select * from myblog.picture ")
    List<Picture> listPicture();

    //添加图片
    @Insert("insert into myblog.picture (pictureaddress," +
            "picturedescription, " +
            "picturename ," +
            "picturetime)" +
            "values ( #{pictureaddress}," +
            "#{picturedescription}," +
            "#{picturename}," +
            "#{picturetime})")
    int savePicture(Picture picture);

    //根据id查询照片
    @Select("select * from myblog.picture where id = #{id}")
    Picture getPicture(Long id);

    //编辑修改相册
    @Update("update myblog.picture set " +
            "pictureaddress = #{pictureaddress} ," +
            "picturedescription = #{picturedescription} ," +
            "picturename = #{picturename} ," +
            "picturetime = #{picturetime}" +
            "where id = #{id}")
    int updatePicture(Picture picture);

    //删除照片
    @Delete("delete from myblog.picture where id = #{id}")
    void deletePicture(Long id);
}
