package com.finnbear.codercanvas;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileSystemManager {
    private ExecutorService _executorService;

    public FileSystemManager() {
        _executorService = Executors.newFixedThreadPool(1);
    }

    public boolean fileExists(String path) {
        return new File(path).exists();
    }

    public void saveObject(Object obj, String path) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(path);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object loadObject(String path) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);

            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }

                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void saveBitmapAsImage(Bitmap bitmap, String path, boolean useThread) {
        if (useThread) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    saveBitmapAsImage(bitmap, path);
                }
            };

            _executorService.submit(runnable);
        } else {
            saveBitmapAsImage(bitmap, path);
        }
    }

    private void saveBitmapAsImage(Bitmap bitmap, String path) {
        BufferedImage bufferedImage = new BufferedImage(bitmap.getWidth(), bitmap.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < bitmap.getWidth(); x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {
                bufferedImage.setRGB(x, y, bitmap.getPixel(x, y));
            }
        }

        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(path + ".png");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ImageIO.write(bufferedImage, "png", bufferedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopExecutorService() {
        _executorService.shutdown();
    }
}
