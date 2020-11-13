package com.example.lesson4.controller;

import com.example.lesson4.dto.OrderDto;
import com.example.lesson4.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit-тесты для Controller
 */
public class OrderControllerTest {
    private OrderController orderController;

    @Mock
    OrderService orderService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderController = new OrderController(orderService);
    }

    @Test
    @DisplayName("Создание заказа")
    void createOrder() {
        OrderDto order = new OrderDto(153, "order", 567, 1);
        Mockito.when(orderService.createOrder(order)).thenReturn(order);
        Assertions.assertEquals(new ResponseEntity<>(order, HttpStatus.OK), orderController.createOrder(order));
    }
}
