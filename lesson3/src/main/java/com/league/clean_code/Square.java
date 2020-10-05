package com.league.clean_code;

/**
 * Шаблон фигуры квадрат
 */
public class Square implements Shape {

    private final double xPos;
    private final double yPos;
    private final double edgeSize;

    /**
     * Конструктор фигуры по двум параметрам координат и длине ребра/стороны
     *
     * @param xPos     координата оси ОХ
     * @param yPos     координата оси ОY
     * @param edgeSize длина ребра/стороны
     */
    public Square(double xPos, double yPos, double edgeSize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.edgeSize = edgeSize;
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public double getEdgeSize() {
        return edgeSize;
    }

    @Override
    public double getPerimeter() {
        return edgeSize * 4;
    }
}