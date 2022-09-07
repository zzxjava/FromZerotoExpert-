package com.fromzerotoexpert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/FromZerotoExpert")
    @ResponseBody
    public String test(){
        return "嗨，欢迎您来到 from zero to expert.";
    }
}
