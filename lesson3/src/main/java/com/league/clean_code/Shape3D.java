package com.league.clean_code;

/**
 * Операции над 3D фигурами
 */
public interface Shape3D {

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
     * Возвращает значение координаты на оси ОZ
     *
     * @return double
     */
    double getZPos();

    /**
     * Возвращает длину стороны/ребра
     *
     * @return double
     */
    double getEdgeSize();

    /**
     * Возвращает значение объема 3D фигуры
     *
     * @return double
     */
    double getVolume();
}