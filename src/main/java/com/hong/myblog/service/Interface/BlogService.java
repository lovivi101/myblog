package com.hong.myblog.service.Interface;


import com.hong.myblog.entity.Blog;
import com.hong.myblog.query.*;

import java.util.List;

/**
* @Description: 博客列表业务层接口
* @Author: hjx
* @Date: 2021/5/11 
*/

public interface BlogService {


    //保存新增博客
    int saveBlog(Blog blog);

    //查询文章管理列表
    List<BlogQuery> getAllBlogQuery();

    //删除博客
    void Delete(Long id );


    List<Blog> getAllBlog();
    //查询编辑修改的文章
    ShowBlog getBlogById(Long id);

    //编辑修改文章
    int updateBlog(ShowBlog showBlog);

    //搜索博客管理列表
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    //查询首页最新博客列表信息

    List<FirstPageBlog> getFirstPageBlog();


    //查询首页最新推荐信息
    List<RecommendBlog> getAllRecommendBlog();

    DetailedBlog getDetailedBlog(Long id);

    //搜索博客列表
    List<FirstPageBlog> getSearchBlog(String query);

    //统计博客总数
    Integer getBlogTotal();

    //统计访问总数
    Integer getBlogViewTotal();

    //统计评论总数
    Integer getBlogCommentTotal();

    //统计留言总数
    Integer getBlogMessageTotal();

    //查询所有分类
    List<FirstPageBlog> getByTypeId(Long typeId);
}
