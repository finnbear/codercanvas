package com.finnbear.codercanvas.client;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Main {
    public static void main(String[] args) {
        CanvasClient canavasClient = new CanvasClient();

        System.out.println(canavasClient.testConnection());

        System.out.println(canavasClient.getPixel(100, 100));

        int white = 255 << 16 | 255 << 8 | 255;

        for (int x = 500; x < 700; x++) {
            for (int y = 600; y < 800; y++) {
                canavasClient.setPixel(x, y, white);
            }
        }
    }
}
