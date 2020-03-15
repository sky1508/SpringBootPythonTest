package com.sky.test1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Test1Controller {

    private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);

    @GetMapping("/hello")
    public String helloWorld() {
        System.out.println("hello invoked");
        logger.info("Test1Controller - hello invoked");
        return "Hello World";
    }
}
