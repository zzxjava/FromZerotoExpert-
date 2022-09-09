package com.fromzerotoexpert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    @GetMapping("/FromZerotoExpert")
    @ResponseBody
    public void test(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        //遍历cookie数组
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie:cookies) {
                //获取cookie名称
                String name = cookie.getName();
                //判断是不是最后一次登录的信息
                if("lastTime".equals(name)){
                    //如果相等，说明已经登录了
                    flag = true;//表示有lasttime的cookie
                    //设置cookie的value
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");//这里不能有空格
                    String str_date = sdf.format(date);
                    //设置cookie的value
                    cookie.setValue(str_date);
                    //设置cookie存活时间
                    cookie.setMaxAge(24*60*60);
                    response.addCookie(cookie);

                    //获取cookie时间,响应数据
                    String value = cookie.getValue();
                    response.getWriter().write("嗨，欢迎您再次来到from zero to expert."+value);
                }
            }
        }
        if(cookies == null || cookies.length == 0|| flag == false){
            //说明是第一次访问
            //设置cookie的value
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            String str_date = sdf.format(date);
            Cookie cookie = new Cookie("lastTime",str_date);
            //设置cookie存活时间
            cookie.setMaxAge(24*60*60);
            response.addCookie(cookie);
            response.getWriter().write("嗨，欢迎您来到from zero to expert.");
        }

    }


    /**
     * 利用@CookieValue注解记录cookie
     * @param visit
     * @param httpServletResponse
     * @return
     */

    @GetMapping("/FromZerotoExpert2")
    @ResponseBody
    public String Test(@CookieValue(value = "testcookie",defaultValue = "false")String visit,HttpServletResponse httpServletResponse){
        if(visit.equals("false")){
            Cookie cookie = new Cookie("testcookie","true");
            //设置cookie有效时间为一天
            cookie.setMaxAge(24*60*60);
            httpServletResponse.addCookie(cookie);
            return "嗨，欢迎您来到 from zero to expert.";
        }

        return "嗨，欢迎您再次来到 from zero to expert.";
    }
}


