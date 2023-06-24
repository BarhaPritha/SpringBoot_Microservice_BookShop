package com.bjit.PaymentApp.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-app")
public interface UserFeignClientsConfig {

    @GetMapping("user/balance/{userId}-{pay}")
    public Long balance(@PathVariable String userId, @PathVariable String pay);

}
