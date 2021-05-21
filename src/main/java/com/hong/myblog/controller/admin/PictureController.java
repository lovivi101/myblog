package com.hong.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hong.myblog.entity.Picture;
import com.hong.myblog.service.Interface.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 照片墙后台管理控制器
 * @author: hjx
 * @time: 2021年05月12日 14:01
 */

@Controller
@RequestMapping("/admin")
public class PictureController {

    @Autowired
    private PictureService pictureService ;

    /**
    * @Description:查询所有照片
    * @Author: hjx
    * @Date: 2021/5/12
    */
    @GetMapping("/pictures")
    public String picture(Model model , @RequestParam(defaultValue = "1" , value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum , 10);
        List<Picture> pictures = pictureService.listPicture();
        PageInfo<Picture> pageInfo = new PageInfo<>(pictures);
//        model.addAttribute("picture" , pictures);
        model.addAttribute("pageInfo" ,  pageInfo);
        return "admin/pictures";
    }


    /**
     * @Description:转到新增页面
     * @Author: hjx
     * @Date: 2021/5/12
     */
    @GetMapping("/pictures/input")
    public String input(Model model){
        model.addAttribute("picture" , new Picture());
        return "admin/pictures-input";
    }



    /**
     * @Description:新增图片
     * @Author: hjx
     * @Date: 2021/5/12
     */
    @PostMapping("/pictures")
    public String post(@Valid Picture picture , BindingResult result , RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/pictures-input";
        }
        int flag = pictureService.savePicture(picture);
        if (flag == 0){
            attributes.addFlashAttribute("message" , "新增失败");
        }
        else attributes.addFlashAttribute("message" , "新增成功");
        return "redirect:/admin/pictures";
    }


    /**
     * @Description:转到修改页面
     * @Author: hjx
     * @Date: 2021/5/12
     */
    @GetMapping("/pictures/{id}/input")
    public String editInput(@PathVariable Long id , Model model){
        model.addAttribute("picture" , pictureService.getPicture(id));
        return "admin/pictures-input";
    }


    /**
     * @Description:修改图片
     * @Author: hjx
     * @Date: 2021/5/12
     */
    @PostMapping("/pictures/{id}")
    public String editPost(@Valid Picture picture , RedirectAttributes attributes){
        int flag = pictureService.updatePicture(picture);
        if (flag == 0){
            attributes.addFlashAttribute("message" , "修改失败");
        }
        else attributes.addFlashAttribute("message" , "修改成功");
        return "redirect:/admin/pictures";
    }


    //删除图片
    /**
     * @Description:删除图片
     * @Author: hjx
     * @Date: 2021/5/12
     */
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes attributes){
        pictureService.deletePicture(id);
        attributes.addFlashAttribute("message" , "删除成功");
        return "redirect:/admin/pictures";
    }
}
