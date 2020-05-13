package com.example.carnet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

        @GetMapping("login")
        public String getLoginView(){
            return "login"; //same name as the one in templates without extension
        }
        @GetMapping("users")
        public String getUserView(){
            return "homepage";
        }
}
