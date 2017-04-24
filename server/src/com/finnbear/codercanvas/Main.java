package com.finnbear.codercanvas;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Main {
    public static void main(String[] args) {
        FileSystemManager fileSystemManager = new FileSystemManager();

        SharedBitmap sharedBitmap1 = new SharedBitmap(10, 10);

        sharedBitmap1.setPixel(5, 5, 255, 0);
        sharedBitmap1.setPixel(3, 3, 255 << 16, 0);
        sharedBitmap1.setPixel(1, 1, 255 << 8, 0);

        sharedBitmap1.saveBitmap(fileSystemManager, "bitmap1");

        SharedBitmap sharedBitmap2 = new SharedBitmap(10, 10);

        sharedBitmap2.loadBitmap(fileSystemManager, "bitmap1");

        System.out.println(sharedBitmap2.getPixel(0, 0)); // Output: 0
        System.out.println(sharedBitmap2.getPixelOwner(0, 0)); // Output: -1
        System.out.println(sharedBitmap2.getPixel(5, 5)); // Output: 255
        System.out.println(sharedBitmap2.getPixelOwner(5, 5)); // Output: 0

        fileSystemManager.saveBitmapAsImage(sharedBitmap2, "bitmap2", true);
    }
}
