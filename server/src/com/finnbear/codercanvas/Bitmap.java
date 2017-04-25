package com.finnbear.codercanvas;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Bitmap {
    private int _width;
    private int _height;
    private int[][] _pixels;

    boolean _modified;

    public Bitmap(int width, int height) {
        _width = width;
        _height = height;
        _pixels = new int[width][height];

        _modified = true;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public boolean checkPixel(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    public synchronized int getPixel(int x, int y) {
        return _pixels[x][y];
    }

    public synchronized void setPixel(int x, int y, int rgb) {
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

    public boolean getModified() {
        return _modified;
    }

    public void setModified(boolean modified) {
        _modified = modified;
    }
}
