package com.hong.myblog.dao;

import com.hong.myblog.entity.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: hjx
 * @time: 2021年05月10日 20:44
 */

@Mapper
@Repository
public interface TypeDao {

    //新增保存分类
    @Insert("insert into myblog.type values (#{id},#{name} )")
    int saveType(Type type);

    //根据id查询分类
    @Select("select * from myblog.type where id = #{id}")
    Type getType(Long id);


    //查询所有分类
    @Select("select * from myblog.type")
    List<Type> getAllType();

    //根据分类名称查询分类
    @Select("select * from myblog.type where name = #{name}")
    Type getTypeByName(String name);

    //编辑修改分类
    @Update("update myblog.type set name = #{name} where id = #{id}")
    int updateType(Type type);

    //删除分类
    @Delete("delete from myblog.type where id = #{id}")
    void deleteType(Long id);

    //查询所有分类
    @Select("select t.id tid, t.name, b.id bid, b.title,b.type_id " +
            " from myblog.type t,myblog.blog b " +
            " where t.id = b.type_id ")
    List<Type> getAllTypeAndBlog();
}
