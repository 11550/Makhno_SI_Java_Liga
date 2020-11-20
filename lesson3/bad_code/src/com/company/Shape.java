package com.company;

/**
 * интерфейс с методами для 2D фигур
 */
public interface Shape {
    /**
     * @return Координата центральной точки по оси ОХ
     */
    double getX();

    /**
     * @return Координата центральной точки по оси ОY
     */
    double getY();

    /**
     * @return Площадь фигуры
     */
    double getSquare();
}
