package com.example.lesson4.service;

import com.example.lesson4.dao.OrderDao;
import com.example.lesson4.dto.OrderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit-тесты для OrderService
 */
public class OrderServiceTest {
    private OrderService orderService;

    @Mock
    OrderDao orderDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderDao);
    }

    @Test
    void createOrder() throws Exception {
        OrderDto order = new OrderDto(153, "order", 121, 1);
        Mockito.when(orderDao.createOrder(order)).thenReturn(order);
        Assertions.assertEquals(order, orderService.createOrder(order));
    }


}
