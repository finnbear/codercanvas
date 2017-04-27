package com.finnbear.codercanvas.client;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Main {
    public static void main(String[] args) {
        CanvasClient canvasClient = new CanvasClient();

        System.out.println(canvasClient.testConnection());

        System.out.println(canvasClient.getPixel(100, 100));

        int white = 255 << 16 | 255 << 8 | 255;

        int color = 100 << 16 | 200 << 8 | 80;

        int[][] invader = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        for (int i = 0; i < 10; i++) {
            int x1 = (int)(Math.random() * 1000) + 10;
            int y1 = (int)(Math.random() * 1000) + 10;


            for (int x = 0; x < invader[0].length; x++) {
                for (int y = 0; y < invader.length; y++) {
                    if (invader[y][x] == 1) {
                        canvasClient.setPixel(x1 + x, y1 + y, color);
                    }
                }
            }
        }
    }
}
