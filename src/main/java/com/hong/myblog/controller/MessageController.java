package com.hong.myblog.controller;

import com.hong.myblog.entity.Message;
import com.hong.myblog.entity.User;
import com.hong.myblog.service.Interface.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 留言页面控制器
 * @author: hjx
 * @time: 2021年05月18日 15:00
 */

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;


//    @Value("${message.avatar}")
    private String avatar = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic4.zhimg.com%2F50%2Fv2-0e98d843ef66ae5e9ec846a7c5f98224_hd.jpg&refer=http%3A%2F%2Fpic4.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624077902&t=c1f80d377ae461a030a0529e09451f9a";


    /**
    * @Description: 跳转评论首页
    * @Author: hjx
    * @Date: 2021/5/18
    */
    @GetMapping("/message")
    public String message(){
        return "message";
    }

    /**
     * @Description: 查询留言
     * @Author: hjx
     * @Date: 2021/5/18
     */
    @GetMapping("/messageCount")
    public String messages(Model model){
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages" , messages);
        return "message";
    }

    /**
     * @Description: 新增留言
     * @Author: hjx
     * @Date: 2021/5/18
     */
    @PostMapping("/message")
    public String post(Message message , HttpSession session , Model model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message";
    }


    /**
     * @Description: 删除留言
     * @Author: hjx
     * @Date: 2021/5/18
     */
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id){
        messageService.deleteMessage(id);
        return "redirect:/message";
    }

}
