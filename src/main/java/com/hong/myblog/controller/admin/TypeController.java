package com.hong.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hong.myblog.entity.Type;
import com.hong.myblog.service.Interface.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @description:  分类管理控制器
 * @Controller注解：用于标注控制层组件
 * @RequestMapping("/admin")：建立请求URL和处理方法之间的对应关系
 * @GetMapping注解：一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写，用于将HTTP get请求映射到特定处理程序的方法注解
 * @PostMapping注解：一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写，用于将HTTP post请求映射到特定处理程序的方法注解
 * @Valid注解：请求数据校验，用来判断是否有重复的分类
 * @PathVariable注解：获取URL中的数据
 * attributes.addFlashAttribute：相当于重定向后，在URL后面拼接了参数，这样在重定向之后的页面后者控制器再去获取URL后年的参数就可以了
 * @author: hjx
 * @time: 2021年05月10日 21:10
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;


    /** 
    * @Description: 分页查询分类列表
    * @Author: hjx
    * @Date: 2021/5/11 
    */
    @GetMapping("/types")
    public String list(Model model , @RequestParam(defaultValue = "1" , value = "pageNum") Integer pageNum){

        //按照排序字段 倒序排序
        String orderBy = "id desc" ;
        PageHelper.startPage(pageNum , 10 , orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo" , pageInfo);
        return "admin/types" ;
    }

    /** 
    * @Description:  返回新增分类页面
    * @Author: hjx
    * @Date: 2021/5/11 
    */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type" , new Type());
        return "admin/types-input";
    }

    /** 
    * @Description: 添加分类
    * @Author: hjx
    * @Date: 2021/5/11 
    */
    @PostMapping("/types")
    public String post(@Valid Type type , RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1!=null){
            attributes.addFlashAttribute("message" , "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int flag = typeService.saveType(type);
        if (flag==0){
            attributes.addFlashAttribute("message" , "新增失败");
        }
        else attributes.addFlashAttribute("message" , "新增成功");
        return "redirect:/admin/types";
    }

    /** 
    * @Description: 跳转修改分类页面
    * @Author: hjx
    * @Date: 2021/5/11 
    */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id , Model model){
        model.addAttribute("type" , typeService.getType(id));
        return "admin/types-input";
    }

    /** 
    * @Description: 编译分类
    * @Author: hjx
    * @Date: 2021/5/11 
    */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type , RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1!=null){
            attributes.addFlashAttribute("message" , "不能添加重复类");
        }
        int flag = typeService.updateType(type);
        if (flag==0){
            attributes.addFlashAttribute("message" , "编译失败");
        }
        else attributes.addFlashAttribute("message" , "编译成功");
        return "redirect:/admin/types";
    }

    /** 
    * @Description: 删除分类
    * @Author: hjx
    * @Date: 2021/5/11 
    */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id  , Model model){
        typeService.deleteType(id);
        model.addAttribute("message" , "删除成功");
        return "redirect:/admin/types";
    }

}
