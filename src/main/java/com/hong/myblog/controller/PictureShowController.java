package com.hong.myblog.controller;

import com.hong.myblog.service.Interface.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 照片墙页面显示控制器
 * @author: hjx
 * @time: 2021年05月14日 10:35
 */
@Controller
public class PictureShowController {
    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture")
    public String friends(Model model) {
        model.addAttribute("pictures",pictureService.listPicture());
        return "picture";
    }
}
