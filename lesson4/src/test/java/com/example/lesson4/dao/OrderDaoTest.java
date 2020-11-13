package com.example.lesson4.dao;

import com.example.lesson4.dto.OrderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit-тесты для OrderDao
 */
public class OrderDaoTest {
    private OrderDao orderDao;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private KeyHolder keyHolder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderDao = new OrderDao(jdbcTemplate, keyHolder);
    }

    @Test
    void createOrder() {
        OrderDto order = new OrderDto(1354, "order", 514, 1);
        Integer id = 1;
        Mockito.when(keyHolder.getKey()).thenReturn(id);
        Assertions.assertEquals(id, orderDao.createOrder(order).getId());
        int affectedRows = 1;
        Mockito.when(jdbcTemplate.update(any(PreparedStatementCreator.class), any(KeyHolder.class))).thenReturn(affectedRows);
        verify(jdbcTemplate, times(1)).update(any(PreparedStatementCreator.class), any(KeyHolder.class));
        verify(keyHolder, times(1)).getKey();
    }
}
