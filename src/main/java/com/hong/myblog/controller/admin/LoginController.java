package com.hong.myblog.controller.admin;

import com.hong.myblog.entity.User;
import com.hong.myblog.service.Implement.UserService_imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @description: 用户登陆控制器
 * @author: hjx
 * @time: 2021年05月10日 17:24
 */

@Controller
@RequestMapping("admin")
public class LoginController {

    @Autowired
    private UserService_imp userService;

    /** 
    * @Description: 跳转登陆页面
    * @Param:  * @param null 
    * @return:  
    * @Author: hjx
    * @Date: 2021/5/10 
    */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username , @RequestParam String password ,
                        HttpSession session , RedirectAttributes attributes){
        User user = userService.CheckUser(username, password);
        if (user!=null){
            System.out.println("登陆成功");
            user.setPassword(null);
            session.setAttribute("user" , user);
            return "admin/index";
        }
        else {
            attributes.addFlashAttribute("message" , "登陆密码错误");
            return "redirect:/admin";
        }
    }

}
