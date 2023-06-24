package com.bjit.PaymentApp.service.impl;

import com.bjit.PaymentApp.entity.PaymentEntity;
import com.bjit.PaymentApp.model.PaymentRequestModel;
import com.bjit.PaymentApp.repository.PaymentRepository;
import com.bjit.PaymentApp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<Object> getPayment(Long id) {
        Optional<PaymentEntity> Payment;
        Payment = paymentRepository.findById(id);
        if (Payment.isEmpty()){
            return new ResponseEntity<>("No payment found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            PaymentEntity paymentEntity = PaymentEntity.builder()
                    .paymentId(Payment.get().getPaymentId())
                    .total(Payment.get().getTotal())
                    .paymentType(Payment.get().getPaymentType())
                    .paymentDone(Payment.get().getPaymentDone())
                    .build();
            return new ResponseEntity<>(paymentEntity, HttpStatus.FOUND);
        }
    }

    @Override
    public PaymentEntity getPaymentById(Long id) {
        Optional<PaymentEntity> Payment;
        Payment = paymentRepository.findById(id);
        if (Payment.isEmpty()){
            return null;
        }
        else {
            PaymentEntity paymentEntity = PaymentEntity.builder()
                    .paymentId(Payment.get().getPaymentId())
                    .total(Payment.get().getTotal())
                    .paymentType(Payment.get().getPaymentType())
                    .paymentDone(Payment.get().getPaymentDone())
                    .build();
            return paymentEntity;
        }
    }

    @Override
    public ResponseEntity<Iterable<PaymentEntity>> getPaymentByPaymentType(String type) {
        Iterable<PaymentEntity> payments = paymentRepository.findAll();
        List<PaymentEntity> b = new ArrayList<PaymentEntity>();
        for(PaymentEntity payment:payments) {
            if(payment.getPaymentType().equals(type)){
                b.add(payment);
            }
        }
        return new ResponseEntity<>(b, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Iterable<PaymentEntity>> getAllPayments() {
        Iterable<PaymentEntity> payments = paymentRepository.findAll();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createPayment(PaymentRequestModel requestModel) {
        PaymentEntity Payment = PaymentEntity.builder()
                .paymentType(requestModel.getPaymentType())
                .total(requestModel.getTotal())
                .paymentDone(requestModel.getPaymentDone())
                .build();

        PaymentEntity savedPayment = paymentRepository.save(Payment);

        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @Override
    public Object newPayment(String id, Long total, String type) {
        PaymentEntity savedPayment;
        Optional<PaymentEntity> payment = paymentRepository.findById(Long.parseLong(id));
        if (payment.isEmpty()){
            savedPayment = PaymentEntity.builder()
                    .paymentType(type)
                    .total(total)
                    .paymentDone(true)
                    .build();
        }
        else {
            payment.get().setPaymentType(type);
            payment.get().setTotal(total);
            payment.get().setPaymentDone(true);
            savedPayment = paymentRepository.save(payment.get());
        }
        return paymentRepository.save(savedPayment);
    }

    @Override
    public ResponseEntity<Object> updatePayment(Long id, PaymentRequestModel requestModel) {
        Optional<PaymentEntity> payment = paymentRepository.findById(id);
        if (payment.isEmpty()){
            return new ResponseEntity<>("No payment found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            payment.get().setPaymentType(requestModel.getPaymentType());
            payment.get().setTotal(requestModel.getTotal());
            payment.get().setPaymentDone(requestModel.getPaymentDone());

            PaymentEntity savedPayment = paymentRepository.save(payment.get());

            return new ResponseEntity<>(savedPayment, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> deletePayment(Long id){
        Optional<PaymentEntity> payment = paymentRepository.findById(id);
        if (payment.isEmpty()){
            return new ResponseEntity<>("No payment found with this id!", HttpStatus.NOT_FOUND);
        }
        else{
            paymentRepository.deleteById(id);
            return new ResponseEntity<>("Payment Deleted!", HttpStatus.OK);
        }
    }

}
