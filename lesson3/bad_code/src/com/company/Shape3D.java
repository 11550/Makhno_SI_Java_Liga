package com.company;

/**
 * интерфейс с методами для 3D фигур
 */
public interface Shape3D {
    /**
     * @return Координата центральной точки по оси ОХ
     */
    double getX();

    /**
     * @return Координата центральной точки по оси ОY
     */
    double getY();

    /**
     * @return Координата центральной точки по оси ОZ
     */
    double getZ();

    /**
     * @return Объем фигуры
     */
    double getVolume();
}
