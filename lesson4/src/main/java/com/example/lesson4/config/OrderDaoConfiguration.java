package com.example.lesson4.config;

import com.example.lesson4.dao.OrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

/**
 * Конфигурации для dao-класса
 */
@Configuration
@RequiredArgsConstructor
public class OrderDaoConfiguration {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public OrderDao orderDao() {
        return new OrderDao(jdbcTemplate, new GeneratedKeyHolder());
    }
}
