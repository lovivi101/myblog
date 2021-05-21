package com.hong.myblog.dao;


import com.hong.myblog.entity.Blog;
import com.hong.myblog.query.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/** 
* @Description: 博客管理持久层接口
* @Author: hjx
* @Date: 2021/5/11 
*/

@Mapper
@Repository
public interface BlogDao {



    ShowBlog getBlogById(Long id);


    @Select("select * from myblog.blog ")
    List<Blog> getAllBlog();

    List<BlogQuery> getAllBlogQuery();

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);


    @Delete("delete from myblog.blog where id = #{id}")
    void deleteBlog(Long id);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<FirstPageBlog> getFirstPageBlog();

    List<RecommendBlog> getAllRecommendBlog();

//    List<FirstPageBlog> getNewBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    @Update("update myblog.blog b set b.views = b.views+1 where b.id = #{id}")
    int updateViews(Long id);

    //    根据博客id查询出评论数量

    @Update("update myblog.blog b set b.comment_count = (" +
            "select count(*) from myblog.comment c where c.blog_id = #{id} and b.id = #{id}" +
            ") WHERE b.id = #{id}")
    int getCommentCountById(Long id);


//    分类页面查询
    List<FirstPageBlog> getByTypeId(Long typeId);


    //统计博客总数
    @Select("select count(*) from myblog.blog ")
    Integer getBlogTotal();

    //统计访问总数
    @Select("select coalesce (sum(views),0) from myblog.blog ")
    Integer getBlogViewTotal();

    //统计评论总数
    @Select("select count(*) from myblog.comment ")
    Integer getBlogCommentTotal();

    //统计留言总数
    @Select("select count(*) from myblog.message ")
    Integer getBlogMessageTotal();


}
