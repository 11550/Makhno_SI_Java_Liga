package com.example.lesson4.controller;

import com.example.lesson4.dto.OrderDto;
import com.example.lesson4.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST контроллер для заказов
 */
@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws RuntimeException {
        OrderDto curOrder = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.OK).body(curOrder);
    }
}
