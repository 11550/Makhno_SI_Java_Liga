package com.example.lesson4.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * dto для удобной передачи JSON
 */
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDto {
    Integer id;
    String name;
    Integer price;
    Integer customerId;
}
