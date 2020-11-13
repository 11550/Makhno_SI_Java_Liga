package com.example.lesson4.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

/**
 * Шаблон заказов
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    /**
     * ID заказа
     */
    @Id
    private Integer id;

    /**
     * Название заказа
     */
    @NonNull
    private String name;

    /**
     * Стоимость заказа
     */
    @NonNull
    private Integer price;
}
