package com.bjit.PaymentApp.config;

import com.bjit.PaymentApp.entity.PaymentEntity;
import com.bjit.PaymentApp.model.PaymentRequestModel;
import com.bjit.PaymentApp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    Logger logger = Logger.getLogger("PaymentController");

    @Autowired
    OrderFeignClientsConfig orderFeignClientsConfig;

    @Autowired
    UserFeignClientsConfig userFeignClientsConfig;

    @GetMapping("pay/{id}-{userId}-{type}")
    public Map<String, String> getPayment(@PathVariable String id, @PathVariable String userId, @PathVariable String type){
        HashMap<String, String> map = new HashMap<>();
        PaymentEntity payment = paymentService.getPaymentById(Long.parseLong(id));
        if(payment==null){
            logger.info("No payment found with this ID!");
            map.put("Error", "No payment found with this ID!");
            return map;
        }
        else {
            Boolean done = payment.getPaymentDone();
            if(done){
                logger.info("Payment is complete with this payment ID!");
                map.put("Error", "Payment is complete with this payment ID!");
                return map;
            }
            else {
                Long total = orderFeignClientsConfig.getPayment(id);
                if (total == 0L) {
                    logger.info("No order made with this payment ID!");
                    map.put("Error", "No order made with this payment ID!");
                    return map;
                } else {
                    Long balance = userFeignClientsConfig.balance(userId, String.valueOf(total));
                    if (balance == null) {
                        logger.info("No user found with this ID!");
                        map.put("Error", "No user found with this ID!");
                        return map;
                    } else {
                        if(balance < total) {
                            logger.info("Insufficient balance of user!");
                            map.put("Error", "Insufficient balance of user!");
                            return map;
                        } else {
                            paymentService.newPayment(id, total, type);
                            logger.info("Payment ID: " + id + ", Total Payment: " + total + ", Payment Type: " + type + ", User new balance: " + balance + ", Payment status: Complete");
                            map.put("Payment ID", id);
                            map.put("Total Payment", String.valueOf(total));
                            map.put("Payment Type", type);
                            map.put("User new balance", String.valueOf(balance));
                            map.put("Order payment status", "Complete");
                            return map;
                        }
                    }
                }
            }
        }
    }

    @GetMapping("/id/{PaymentId}")
    public ResponseEntity<Object> getPayment(@PathVariable String PaymentId) {
        return paymentService.getPayment(Long.parseLong(PaymentId));
    }

    @GetMapping("/get/{id}")
    public String getPaymentById(@PathVariable String id) {
        PaymentEntity payment = paymentService.getPaymentById(Long.parseLong(id));
        if(payment==null){
            logger.info("No payment found!");
            return "No payment found!";
        }
        else if(payment.getPaymentDone()){
            logger.info("Payment complete!");
            return "Payment complete!";
        }
        else {
            logger.info("Found!");
            return "Found!";
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> newPayment(@RequestBody PaymentRequestModel Payment) {
        return paymentService.createPayment(Payment);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updatePayment(@PathVariable String id, @RequestBody PaymentRequestModel Payment) {
        return paymentService.updatePayment(Long.parseLong(id), Payment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePayment(@PathVariable String id) {
        return paymentService.deletePayment(Long.parseLong(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<PaymentEntity>> allPayment() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Iterable<PaymentEntity>> getPaymentByPayment(@PathVariable String type) {
        return paymentService.getPaymentByPaymentType(type);
    }

}
