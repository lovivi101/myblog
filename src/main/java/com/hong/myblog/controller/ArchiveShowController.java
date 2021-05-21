package com.hong.myblog.controller;

import com.hong.myblog.query.BlogQuery;
import com.hong.myblog.service.Interface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @description: 时间轴页面显示控制器
 * @author: hjx
 * @time: 2021年05月14日 10:32
 */

@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<BlogQuery> blogs = blogService.getAllBlogQuery();
        model.addAttribute("blogs", blogs);
        return "archives";
    }
}
