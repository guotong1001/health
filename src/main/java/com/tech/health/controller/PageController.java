package com.tech.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class PageController {


    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }

    /**
     * 饮食推荐
     * @return
     */
    @RequestMapping("/recommend")
    public String ystj(){
        return "user/recommend";
    }

}
