package com.hong.myblog.service.Interface;

import com.hong.myblog.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageService {
    //添加一个评论
    int saveMessage(Message message);

    //查询父级评论
    List<Message> listMessage();


    //删除评论
    void deleteMessage(Long id);
}
