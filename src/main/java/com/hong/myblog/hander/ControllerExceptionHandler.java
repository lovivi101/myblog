package com.hong.myblog.hander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:  拦截异常处理
 * 对于404和500错误页面，SpringBoot可以根据页面的命名方式找到对应的文件，
 * 而自定义的错误就需要我们自己来拦截了，让代码出现问题的时候跳转到我们自己
 * 定义的错误页面，这里就需要自定义拦截器。
 *
 * @ControllerAdvice表示拦截掉所有带有@Controller注解的控制器
 * @ExceptionHandler表明是异常处理方法
 * ModelAndView：返回一个页面信息
 * 通过拦截异常信息，在日志中记录，并返回给error页面
 * 标识了状态码的时候就不拦截，如资源找不到异常
 * @author: hjx
 * @time: 2021年05月10日 11:03
 */

@ControllerAdvice
public class ControllerExceptionHandler {


    /*将异常记录到日志*/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request , Exception e ) throws Exception {


        /*记录异常信息：请求的URL，异常信息*/
        logger.error("Request URL : {}，Exception : {}" , request.getRequestURL() , e);

        /*当标识了状态码的时候就不拦截*/
        if (AnnotationUtils.findAnnotation(e.getClass() , ResponseStatus.class)!=null){
            throw e;
        }


        /*将错误信息显示到错误页面*/
        ModelAndView mv = new ModelAndView();
        mv.addObject("url" , request.getRequestURL());
        mv.addObject("Exception" , e);
        mv.setViewName("error/error");
        return mv;
    }

}
