package com.league.clean_code;

/**
 * Шаблон фигуры куб
 */
public class Qube implements Shape3D {

    private final double xPos;
    private final double yPos;
    private final double zPos;
    private final double edgeSize;

    /**
     * Конструктор фигуры по трем параметрам координат и длине ребра/стороны
     *
     * @param xPos     координата оси ОХ
     * @param yPos     координата оси ОY
     * @param zPos     координата оси ОZ
     * @param edgeSize длина ребра/стороны
     */
    public Qube(double xPos, double yPos, double zPos, double edgeSize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
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
    public double getZPos() {
        return zPos;
    }

    @Override
    public double getEdgeSize() {
        return edgeSize;
    }

    @Override
    public double getVolume() {
        return Math.pow(edgeSize, 3);
    }

    @Override
    public double getPerimeter() {
        return edgeSize * 12;
    }
}
