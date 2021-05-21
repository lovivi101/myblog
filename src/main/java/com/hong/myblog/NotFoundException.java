package com.hong.myblog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @description:
 * 对于资源找不到异常，一般也是要跳转到404页面的，这里就需要自定义一个异常类，专门用来应对资源找不到
 * 继承RuntimeException，实现继承RuntimeException的构造函数
 * @ResponseStatus(HttpStatus.NOT_FOUND)注解表示资源找不到的状态码，标识了状态码的时候就不拦截
 * @author: hjx
 * @time: 2021年05月10日 11:22
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
