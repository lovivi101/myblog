package com.hong.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hong.myblog.entity.Blog;
import com.hong.myblog.entity.Type;
import com.hong.myblog.entity.User;
import com.hong.myblog.query.BlogQuery;
import com.hong.myblog.query.SearchBlog;
import com.hong.myblog.query.ShowBlog;
import com.hong.myblog.service.Interface.BlogService;
import com.hong.myblog.service.Interface.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @description:博客管理控制器
 * @author: hjx
 * @time: 2021年05月11日 8:56
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;



    /**
    * @Description: 跳转博客修改页
    * @Author: hjx
    * @Date: 2021/5/11
    */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types" , typeService.getAllType());
        model.addAttribute("blog" , new Blog());
        return "/admin/blogs-input";
    }


    /**
     * @Description: 博客新增
     * @Author: hjx
     * @Date: 2021/5/11
     */
    @PostMapping("/blogs")
    public String post(Blog blog , RedirectAttributes attributes , HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setUserId(blog.getUser().getId());

        int flag = blogService.saveBlog(blog);
        if (flag==0){
            attributes.addFlashAttribute("message" , "添加失败");
        }
        else attributes.addFlashAttribute("message" , "添加成功");

        return "redirect:/admin/blogs";
    }


    /**
     * @Description: 显示博客列表
     * @Author: hjx
     * @Date: 2021/5/11
     */
    @RequestMapping("/blogs")
    public String blogs(Model model , @RequestParam(defaultValue = "1" , value = "pageNum") Integer pageNum ){

        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum , 10 , orderBy);
        List<BlogQuery> blogQuery = blogService.getAllBlogQuery();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogQuery);
        model.addAttribute("types" , typeService.getAllType());
        model.addAttribute("pageInfo" , pageInfo);
        return "/admin/blogs";
    }


    /**
     * @Description: 删除博文
     * @Author: hjx
     * @Date: 2021/5/11
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(RedirectAttributes attributes , @PathVariable Long id){
        blogService.Delete(id);
        attributes.addFlashAttribute("message" , "删除成功");
        /*int flag = blogService.Delete(id);
        if (flag==0){
            attributes.addFlashAttribute("message" , "删除失败");
        }
        else attributes.addFlashAttribute("message" , "删除成功");*/
        return "redirect:/admin/blogs";
    }

    /**
     * @Description: 显示博客修改页列表
     * @Author: hjx
     * @Date: 2021/5/11
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id , Model model){
        ShowBlog blogId = blogService.getBlogById(id);
        List<Type> types = typeService.getAllType();
        model.addAttribute("blog" , blogId);
        model.addAttribute("types" , types);
        return "admin/blogs-input";
    }

    /**
     * @Description: 更新博客修改页列表
     * @Author: hjx
     * @Date: 2021/5/11
     */
    @PostMapping("/blogs/{id}")
    public String editPost(@Valid ShowBlog showBlog , RedirectAttributes attributes){
        int flag = blogService.updateBlog(showBlog);
        if (flag == 0){
            attributes.addFlashAttribute("message" , "编译失败");
        }else
            attributes.addFlashAttribute("message" , "编译成功");
        return "redirect:/admin/blogs";
    }


    /**
     * @Description: 显示博客列表
     * @Author: hjx
     * @Date: 2021/5/11
     */
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }



}
