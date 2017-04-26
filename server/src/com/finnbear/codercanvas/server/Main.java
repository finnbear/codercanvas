package com.finnbear.codercanvas.server;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Main {
    public static void main(String[] args) {
        CanvasServer canvasServer = new CanvasServer(4500);

        new Thread(canvasServer).start();
    }
}