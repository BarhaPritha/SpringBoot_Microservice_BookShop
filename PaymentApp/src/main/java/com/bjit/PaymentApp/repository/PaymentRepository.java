package com.bjit.PaymentApp.repository;

import com.bjit.PaymentApp.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {

    public PaymentEntity findByPaymentType(Long type);

}

