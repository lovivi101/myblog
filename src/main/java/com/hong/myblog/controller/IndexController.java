package com.hong.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hong.myblog.dao.BlogDao;
import com.hong.myblog.entity.Comment;
import com.hong.myblog.query.BlogQuery;
import com.hong.myblog.query.DetailedBlog;
import com.hong.myblog.query.FirstPageBlog;
import com.hong.myblog.query.RecommendBlog;
import com.hong.myblog.service.Interface.BlogService;
import com.hong.myblog.service.Interface.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description:
 * @author: hjx
 * @time: 2021年05月13日 13:51
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService ;

/*    @Autowired
    private BlogDao blogDao;*/

    @Autowired
    private CommentService commentService ;


    /**
    * @Description: 分页查询博客列表
    * @Author: hjx
    * @Date: 2021/5/13
    */
    @GetMapping("/")
    public String index(Model model , @RequestParam(defaultValue = "1" , value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum , 10);
        List<FirstPageBlog> allFirstPageBlog = blogService.getFirstPageBlog();
        List<RecommendBlog> recommendBlog = blogService.getAllRecommendBlog();
        PageInfo<FirstPageBlog> pageInfo  = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo" , pageInfo);
        model.addAttribute("recommendBlogs" , recommendBlog);
        return "index";
    }


    /**
    * @Description: 搜索博客
    * @Author: hjx
    * @Date: 2021/5/17
    */
    @PostMapping("/search")
    public String search(Model model , @RequestParam(defaultValue = "1" , value = "pageNum") Integer pageNum , @RequestParam String query){
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo" , pageInfo);
        model.addAttribute("query" , query);
        return "search";
    }



    /**
     * @Description: 博客信息
     * @Author: hjx
     * @Date: 2021/5/17
     */
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        Integer blogCommentTotal = blogService.getBlogCommentTotal();
        Integer blogTotal = blogService.getBlogTotal();
        Integer blogMessageTotal = blogService.getBlogMessageTotal();
        Integer blogViewTotal = blogService.getBlogViewTotal();
        model.addAttribute("blogCommentTotal" , blogCommentTotal);
        model.addAttribute("blogTotal" , blogTotal);
        model.addAttribute("blogMessageTotal" , blogMessageTotal);
        model.addAttribute("blogViewTotal" , blogViewTotal);
        return "index :: blogMessage";
    }


    /**
     * @Description: 跳转博客详情页面
     * @Author: hjx
     * @Date: 2021/5/17
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }
}
