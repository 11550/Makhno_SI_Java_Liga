package com.company;

/**
 * Шаблон куб
 */
public class Qube implements Shape3D {

    private double x, y, z, edgeSize;

    /**
     * @param x Координата центральной точки по оси ОХ
     * @param y Координата центральной точки по оси ОY
     * @param z Координата центральной точки по оси ОZ
     * @param edgeSize Длина ребра
     */
    public Qube(double x, double y, double z, double edgeSize) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.edgeSize = edgeSize;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}
