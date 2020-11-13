package com.example.lesson4.dao;

import com.example.lesson4.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

/**
 * DAO для работы с заказами
 */
@RequiredArgsConstructor
public class OrderDao {

    /**
     * Запрос для добавления заказа в БД
     */
    final String INSERT_SQL = "INSERT INTO ORDERS (name, price, customer_id) VALUES(?,?,?)";

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder;

    /**
     * Создание заказа
     *
     * @param orderDto -  dto заказа, JSON(id заказа не имеет значения при вводе)
     * @return dto заказа, JSON(id заказа актуальный)
     */
    public OrderDto createOrder(OrderDto orderDto) {
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, orderDto.getName());
                    ps.setInt(2, orderDto.getPrice());
                    ps.setInt(3, orderDto.getCustomerId());
                    return ps;
                }, keyHolder
        );

        orderDto.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return orderDto;
    }
}
