package com.hong.myblog.service.Interface;

import com.hong.myblog.entity.Comment;

import java.util.List;

/**
 * @description: 博客评论业务层接口
 * @author: hjx
 * @time: 2021年05月17日 20:29
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

    //删除评论
    void deleteComment(Comment comment,Long id);
}
