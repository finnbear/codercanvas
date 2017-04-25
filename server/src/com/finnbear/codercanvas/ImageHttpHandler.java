package com.finnbear.codercanvas;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * © 2017 Finn Bear All Rights Reserved
 */

public class ImageHttpHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        File image = new File("bitmap.png");
        BufferedImage bufferedImage = ImageIO.read(image);

        WritableRaster writableRaster = bufferedImage.getRaster();
        DataBufferByte dataBufferByte = (DataBufferByte)writableRaster.getDataBuffer();

        byte[] imageBytes = dataBufferByte.getData();

        httpExchange.getResponseHeaders().set("Pragma", "public");
        httpExchange.getResponseHeaders().set("Cache-Control", "max-age=0");
        httpExchange.getResponseHeaders().set("Content-Type", "image/png");

        OutputStream outputStream = null;

        try {
            httpExchange.sendResponseHeaders(200, imageBytes.length);
            outputStream = httpExchange.getResponseBody();
            
            outputStream.write(imageBytes);

            outputStream.flush();

            System.out.println("ImageHttpHandler - Successfully served image.");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
