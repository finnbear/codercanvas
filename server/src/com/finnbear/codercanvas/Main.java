package com.finnbear.codercanvas;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Main {
    public static void main(String[] args) {
        FileSystemManager fileSystemManager = new FileSystemManager();

        SharedBitmap sharedBitmap1 = new SharedBitmap(100, 100);

        for (int x = 0; x < sharedBitmap1.getWidth(); x++) {
            for (int y = 0; y < sharedBitmap1.getHeight(); y++) {
                sharedBitmap1.setPixel(x, y, (int)(Math.pow(x + y, 2)) << 8, 0);
            }
        }

        sharedBitmap1.saveBitmap(fileSystemManager, "bitmap1");

        SharedBitmap sharedBitmap2 = new SharedBitmap(100, 100);

        sharedBitmap2.loadBitmap(fileSystemManager, "bitmap1");

        System.out.println(sharedBitmap2.getPixel(0, 0)); // Output: 0
        System.out.println(sharedBitmap2.getPixelOwner(0, 0)); // Output: -1
        System.out.println(sharedBitmap2.getPixel(5, 5)); // Output: 255
        System.out.println(sharedBitmap2.getPixelOwner(5, 5)); // Output: 0

        fileSystemManager.saveBitmapAsImage(sharedBitmap2, "bitmap2", true);

        fileSystemManager.stopExecutorService();
    }
}
