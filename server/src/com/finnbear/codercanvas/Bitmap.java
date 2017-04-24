package com.finnbear.codercanvas;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Bitmap {
    private int _width;
    private int _height;
    private int[][] _pixels;

    public Bitmap(int width, int height) {
        _width = width;
        _height = height;
        _pixels = new int[width][height];
    }

    public int getPixel(int x, int y) {
        return _pixels[x][y];
    }

    public void setPixel(int x, int y, int rgb) {
        _pixels[x][y] = rgb;
    }

    public void loadBitmap(String filename) {

    }

    public void saveBitmap(String filename) {

    }
}
