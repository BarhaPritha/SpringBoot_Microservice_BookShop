package com.example.demo.controller;

import com.example.demo.PaymentApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class CountController {

    Logger logger = Logger.getLogger("CountController");

    @GetMapping("/getCount")
    public Integer getCount() {
        Integer count = PaymentApplication.COUNTER++;
        logger.info("Count " + count);
        return count;
    }
}
