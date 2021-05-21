package com.hong.myblog.dao;

import com.hong.myblog.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;



@Mapper
@Repository
public interface CommentDao {

    //根据创建时间倒序来排
    @Select("select *\n" +
            "        from myblog.comment c\n" +
            "        where c.blog_id = #{blogId} and c.parent_comment_id = #{blogParentId}\n" +
            "        order by c.create_time desc")
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询一级回复
    @Select("select *\n" +
            "        from myblog.comment c\n" +
            "        where c.blog_id = #{blogId} and c.parent_comment_id = #{id}\n" +
            "        order by c.create_time desc")
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    //查询二级回复
    @Select("select *\n" +
            "        from myblog.comment c\n" +
            "        where c.blog_id = #{blogId} and c.parent_comment_id = #{childId}\n" +
            "        order by c.create_time desc")
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    //查询父级对象
//    Comment findByParentCommentId(Long parentCommentId);

    //添加一个评论
    @Insert("insert into myblog.comment (nickname,email,content,avatar,create_time,blog_id,parent_comment_id,admin_comment)\n" +
            "        values (#{nickname},#{email},#{content},#{avatar},#{createTime},#{blogId},#{parentCommentId},#{adminComment});")
    int saveComment(Comment comment);

    //删除评论
    @Delete("delete from myblog.comment where id = #{id}")
    void deleteComment(Long id);
}
