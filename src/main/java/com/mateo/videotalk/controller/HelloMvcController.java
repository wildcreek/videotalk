package com.mateo.videotalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/5/28.
 */
@Controller
@RequestMapping("/hello")
public class HelloMvcController {
    @RequestMapping("/mvc")
    //localhost:8080/hello/mvc
    public String helloMvc() {
        return "home";
    }
}
