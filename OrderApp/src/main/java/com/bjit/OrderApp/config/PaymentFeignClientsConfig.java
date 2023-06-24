package com.bjit.OrderApp.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("payment-app")
public interface PaymentFeignClientsConfig {

    @GetMapping("payment/get/{id}")
    public String getPaymentById(@PathVariable String id);

}
