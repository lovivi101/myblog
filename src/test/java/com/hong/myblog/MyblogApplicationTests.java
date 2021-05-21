package com.hong.myblog;

import com.hong.myblog.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyblogApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void test(){
        String password = "123456";
        System.out.println(MD5Util.code(password));
//        ca71d89c9fc2248c65d13d9d58094bda
    }
}
