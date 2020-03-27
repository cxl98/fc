package com.example.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Menu {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
