package com.example.firstservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/hello")
    public String hello(){
        return "hello World!";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome World!";
    }


}
