package com.hong.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 音乐盒页面显示控制器
 * @author: hjx
 * @time: 2021年05月14日 10:33
 */

@Controller
public class MusicShowController {
    @GetMapping("/music")
    public String about() {
        return "music";
    }
}
