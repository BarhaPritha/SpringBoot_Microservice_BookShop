package com.bjit.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("payment-app")
public interface FeignClientsConfig {

}
