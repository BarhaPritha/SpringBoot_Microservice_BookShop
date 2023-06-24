package com.bjit.PaymentApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestModel {

    private Long total = 0L;
    private String paymentType = "cash";
    private Boolean paymentDone = false;

}

