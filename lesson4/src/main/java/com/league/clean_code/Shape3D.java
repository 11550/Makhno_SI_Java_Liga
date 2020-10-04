package com.league.clean_code;

/**
 * Операции над 3D фигурами
 */
public interface Shape3D extends Shape {

    /**
     * Возвращает значение координаты на оси ОZ
     *
     * @return double
     */
    double getZPos();

    /**
     * Возвращает значение объема 3D фигуры
     *
     * @return double
     */
    double getVolume();
}
