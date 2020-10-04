package com.league.clean_code;

/**
 * Операции над 2D фигурами
 */
public interface Shape {

    /**
     * Возвращает значение координаты на оси ОХ
     *
     * @return double
     */
    double getXPos();

    /**
     * Возвращает значение координаты на оси ОY
     *
     * @return double
     */
    double getYPos();

    /**
     * Возвращает длину стороны/ребра
     *
     * @return double
     */
    double getEdgeSize();

    /**
     * Возвращает значение периметра
     *
     * @return double
     */
    double getPerimeter();
}
