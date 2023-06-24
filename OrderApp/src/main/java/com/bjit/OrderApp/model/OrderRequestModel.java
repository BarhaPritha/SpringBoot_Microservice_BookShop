package com.bjit.OrderApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestModel {

    private Long payment;
    private Long book;
    private Long price;
    private Long quantity;
    private Long subTotal;

}

