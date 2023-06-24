package com.bjit.PaymentApp.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("order-app")
public interface FeignClientsConfig {

    @GetMapping("order/pay/{payment}")
    public Long getPayment(@PathVariable String payment);

}
