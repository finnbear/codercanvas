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

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getPixel(int x, int y) {
        return _pixels[x][y];
    }

    public void setPixel(int x, int y, int rgb) {
        _pixels[x][y] = rgb;
    }

    public int[][] getPixels() {
        return _pixels;
    }

    public void setPixels(int[][] pixels) {
        _pixels = pixels;
    }

    public void loadBitmap(FileSystemManager fileSystemManager, String path) {
       setPixels((int[][])fileSystemManager.loadObject(path + "_pixels" + ".dat"));
    }

    public void saveBitmap(FileSystemManager fileSystemManager, String path) {
        fileSystemManager.saveObject(getPixels(), path + "_pixels" + ".dat");
    }
}
