package com.bjit.OrderApp.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("book-app")
public interface BookFeignClientsConfig {

    @GetMapping("books/price/{bookId}")
    public Long price(@PathVariable String bookId);

    @GetMapping("books/name/{bookId}")
    public String name(@PathVariable String bookId);

    @GetMapping("books/inventory/{bookId}-{quantity}")
    public Long inventory(@PathVariable String bookId, @PathVariable String quantity);


}
