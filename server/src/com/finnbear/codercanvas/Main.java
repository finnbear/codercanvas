package com.finnbear.codercanvas;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Main {
    public static void main(String[] args) {
        FileSystemManager fileSystemManager = new FileSystemManager();

        SharedBitmap sharedBitmap = new SharedBitmap(10, 10);

        sharedBitmap.setPixel(5, 5, 255, 0);

        sharedBitmap.saveBitmap(fileSystemManager, "bitmap1");
    }
}
