package com.company;

/**
 * Шаблон Квадрат
 */
public class Square implements Shape {
    private double x, y, edgeSize;

    /**
     * @param x Координата центральной точки по оси ОХ
     * @param y Координата центральной точки по оси ОХ
     * @param edgeSize Длина ребра
     */
    public Square(double x, double y, double edgeSize) {
        this.x = x;
        this.y = y;
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
    public double getSquare() {
        return edgeSize * edgeSize;
    }
}
