package com.finnbear.codercanvas;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class SharedBitmap extends Bitmap {
    private int[][] _ownership;

    public SharedBitmap(int width, int height) {
        super(width, height);

        _ownership = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                _ownership[x][y] = -1;
            }
        }
    }

    public boolean setPixel(int x, int y, int rgb, int owner) {
        super.setPixel(x, y, rgb);

        if (_ownership[x][y] == -1) {
            _ownership[x][y] = owner;

            return true;
        } else {
            return false;
        }
    }

    public boolean setPixelOwner(int x, int y, int owner) {
        if (_ownership[x][y] == -1) {
            _ownership[x][y] = owner;

            return true;
        } else {
            return false;
        }
    }

    public boolean setAreaOwner(int x1, int y1, int x2, int y2, int owner) {
        // Make sure x1 < x2 and y1 < y2
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        } else if (x1 == x2) {
            return false;
        }

        if (y1 > y2) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        } else if (y1 == y2) {
            return false;
        }

        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                if (_ownership[x][y] != -1 || super.getPixel(x, y) != 0) {
                    return false;
                }
            }
        }

        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                setPixelOwner(x, y, owner);
            }
        }

        return true;
    }
}
