package com.hong.myblog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * 由于后面要用到MD5加密，对登录密码进行加密，这里就先进行处理一下，用来放工具类，创建MD5Utils工具类
 * @author: hjx
 * @time: 2021年05月10日 11:59
 */
public class MD5Util {

    public static String code(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] digest = md5.digest();
            int i ;
            StringBuffer buffer = new StringBuffer("");
            for (int j = 0; j < digest.length; j++) {
                i = digest[j];
                if (i<0){
                    i+=256;
                }
                if (i<16)
                    buffer.append("0");

                buffer.append(Integer.toHexString(i));
            }

            return buffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
