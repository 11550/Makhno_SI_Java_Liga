package com.example.lesson4.service;

import com.example.lesson4.dao.OrderDao;
import com.example.lesson4.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Сервис для заказа
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    /**
     * Создание заказа в БД
     *
     * @param order заказ для внесения в БД, JSON
     * @return созданный заказ, JSON
     */
    public OrderDto createOrder(OrderDto order) {
        isCorrectInput(order);
        return orderDao.createOrder(order);
    }

    /**
     * Возвращает ничего;
     * выкинет RuntimeException, если переданы некорректные данные
     *
     * @param order
     */
    private void isCorrectInput(OrderDto order) {
        if (Objects.isNull(order.getCustomerId())
                || Objects.isNull(order.getId())
                || Objects.isNull(order.getName())
                || Objects.isNull(order.getPrice())
                || order.getPrice() <= 0) {
            throw new RuntimeException("Incorrect input data");
        }
    }
}
