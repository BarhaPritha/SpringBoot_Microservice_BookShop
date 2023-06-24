package com.bjit.OrderApp.service.impl;

import com.bjit.OrderApp.service.OrderService;
import com.bjit.OrderApp.entity.OrderEntity;
import com.bjit.OrderApp.model.OrderRequestModel;
import com.bjit.OrderApp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public ResponseEntity<Object> getOrder(Long id) {
        Optional<OrderEntity> Order;
        Order = orderRepository.findById(id);
        if (Order.isEmpty()){
            return new ResponseEntity<>("No order found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            OrderEntity orderEntity = OrderEntity.builder()
                    .orderId(Order.get().getOrderId())
                    .payment(Order.get().getPayment())
                    .book(Order.get().getBook())
                    .price(Order.get().getPrice())
                    .quantity(Order.get().getQuantity())
                    .subTotal(Order.get().getSubTotal())
                    .build();
            return new ResponseEntity<>(orderEntity, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Iterable<OrderEntity>> getOrderByPayment(Long pay) {
        Iterable<OrderEntity> orders = orderRepository.findAll();
        List<OrderEntity> b = new ArrayList<OrderEntity>();
        for(OrderEntity order:orders) {
            if(order.getPayment().equals(pay)){
                b.add(order);
            }
        }
        return new ResponseEntity<>(b, HttpStatus.FOUND);
    }

    @Override
    public Long getPayment(Long payment) {
        Iterable<OrderEntity> orders = orderRepository.findAll();
        List<OrderEntity> b = new ArrayList<OrderEntity>();
        long pay = 0L;
        for(OrderEntity order:orders) {
            if(order.getPayment().equals(payment)){
                b.add(order);
                pay = pay + (order.getPrice() * order.getQuantity());
            }
        }
        return pay;
    }

    @Override
    public ResponseEntity<Iterable<OrderEntity>> getAllOrders() {
        Iterable<OrderEntity> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createOrder(OrderRequestModel requestModel) {
        OrderEntity Order = OrderEntity.builder()
                .payment(requestModel.getPayment())
                .book(requestModel.getBook())
                .price(requestModel.getPrice())
                .quantity(requestModel.getQuantity())
                .subTotal(requestModel.getSubTotal())
                .build();

        OrderEntity savedOrder = orderRepository.save(Order);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @Override
    public Object newOrder(String payment, String id, String quantity, Long price) {
        Long quant = Long.parseLong(quantity);
        OrderEntity Order = OrderEntity.builder()
                .payment(Long.parseLong(payment))
                .book(Long.parseLong(id))
                .price(price)
                .quantity(quant)
                .subTotal(price*quant)
                .build();

        return orderRepository.save(Order);
    }

    @Override
    public ResponseEntity<Object> updateOrder(Long id, OrderRequestModel requestModel) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isEmpty()){
            return new ResponseEntity<>("No order found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            order.get().setPayment(requestModel.getPayment());
            order.get().setBook(requestModel.getBook());
            order.get().setPrice(requestModel.getPrice());
            order.get().setQuantity(requestModel.getQuantity());
            order.get().setSubTotal(requestModel.getSubTotal());

            OrderEntity savedOrder = orderRepository.save(order.get());

            return new ResponseEntity<>(savedOrder, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> deleteOrder(Long id){
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isEmpty()){
            return new ResponseEntity<>("No order found with this id!", HttpStatus.NOT_FOUND);
        }
        else{
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Order Deleted!", HttpStatus.OK);
        }
    }

}
