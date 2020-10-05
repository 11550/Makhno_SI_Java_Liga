package com.league.clean_code;

public class BestCode2 {
    /**
     * Точка входа в программу
     *
     * @param args массив строк
     */
    public static void main(String... args) {
        Qube qube = new Qube(1, 1, 1, 10);
        System.out.println("Qube volume: " + qube.getVolume());

        Square square = new Square(1, 1, 5);
        System.out.println("Square perimeter: " + square.getPerimeter());
    }
}