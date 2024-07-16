package com.example.deploy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class TestController {

    @GetMapping(path = "/testGet")
    public String testGetDeploy() {
        return "This is a Test Get Deploy";
    }

    @GetMapping(path = "/test2")
    public String testGet2() {
        return "This is a Test 2";
    }
}
