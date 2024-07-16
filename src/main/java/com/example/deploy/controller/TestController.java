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
}
