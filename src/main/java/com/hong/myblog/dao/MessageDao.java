package com.hong.myblog.dao;

import com.hong.myblog.entity.Message;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository

public interface MessageDao {

    //添加一个评论
    @Insert("insert into myblog.message (id,\n" +
            "nickname,\n" +
            "email,\n" +
            "content,\n" +
            "avatar,\n" +
            "create_time,\n" +
            "parent_message_id,\n" +
            "admin_message)" +
            "values (#{id},\n" +
            "#{nickname},\n" +
            "#{email},\n" +
            "#{content},\n" +
            "#{avatar},\n" +
            "#{createTime},\n" +
            "#{parentMessageId},\n" +
            "#{adminMessage})")
    int saveMessage(Message message);

    //查询父级评论
    @Select("select * from myblog.message where parent_message_id = #{ParentId} " +
            "order by create_time desc")
    List<Message> findByParentIdNull(@Param("ParentId") Long ParentId);

    //查询一级回复
    @Select("select * from myblog.message where parent_message_id = #{id} " +
            "order by create_time desc")
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    //查询二级以及所有子集回复
    @Select("select * from myblog.message where parent_message_id = #{childId} " +
            "order by create_time desc")
    List<Message> findByReplayId(@Param("childId") Long childId);

    //删除评论
    @Delete("delete from myblog.message where id = #{id}")
    void deleteMessage(Long id);
}
