package com.bjit.SpringProject.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("order-app")
public interface FeignClientsConfig {

}
