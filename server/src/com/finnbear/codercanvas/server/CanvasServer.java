package com.finnbear.codercanvas.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * © 2017 Finn Bear All Rights Reserved
 */

public class CanvasServer implements  Runnable {
    String _bitmapPath = "bitmap";
    Bitmap _bitmap;
    FileSystemManager _fileSystemManager;

    protected boolean _running = false;
    protected int _serverPort;
    protected ServerSocket _serverSocket;
    protected Thread _thread;
    protected ExecutorService _threadPool;

    public CanvasServer(int serverPort) {
        _bitmap = new Bitmap(1024, 1024);

        _fileSystemManager = new FileSystemManager();

        if (_fileSystemManager.fileExists(_bitmapPath + "_pixels" + ".dat")) {
            _bitmap.loadBitmap(_fileSystemManager, _bitmapPath);
        }

        _serverPort = serverPort;
        _threadPool = Executors.newFixedThreadPool(16);
    }

    public void run() {
        System.out.println("CanvasServer - Starting server...");

        synchronized (this) {
            this._thread = Thread.currentThread();
        }

        _running = true;

        try {
            this._serverSocket = new ServerSocket(this._serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("CanvasServer - Server started.");

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (_bitmap.getModified()) {
                System.out.println("CanvasServer - Saving bitmap...");
                _bitmap.saveBitmap(_fileSystemManager, _bitmapPath);
                _fileSystemManager.saveBitmapAsImage(_bitmap, _bitmapPath, true);

                _bitmap.setModified(false);
            }
        }, 0, 10000, TimeUnit.MILLISECONDS);

        while (_running) {
            Socket clientSocket = null;

            try {
                clientSocket = this._serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ConnectionHandler socketRunnable = new ConnectionHandler(clientSocket, _bitmap);

            this._threadPool.execute(socketRunnable);
        }

        this._threadPool.shutdown();
        _fileSystemManager.stopExecutorService();

        System.out.println("CanvasServer - Server stopped.");
    }
}