package com.spring.mini.project.demo.spring.mini.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Article {
    @GetMapping("/getAll")
    public String getAll(){
        return "Hello";
    }
}
