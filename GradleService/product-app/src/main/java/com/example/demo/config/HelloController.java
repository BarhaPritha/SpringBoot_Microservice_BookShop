package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
    Logger logger = Logger.getLogger("HelloController");

    @Autowired
    FeignClientsConfig feignClientsConfig;

    @GetMapping("/hello")
    public String hello (){
        Integer count = feignClientsConfig.getCount();
        logger.info("Hello " + count);
        return "Hello service " + count;
    }
}
