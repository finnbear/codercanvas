package com.finnbear.codercanvas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * © 2017 Finn Bear All Rights Reserved
 */

public class CanvasServer implements  Runnable {
    Bitmap _bitmap;
    FileSystemManager _fileSystemManager;

    protected boolean _running = false;
    protected int _serverPort;
    protected ServerSocket _serverSocket;
    protected Thread _thread;
    protected ExecutorService _threadPool;

    public CanvasServer(int serverPort) {
        _bitmap = new Bitmap(256, 256);

        _fileSystemManager = new FileSystemManager();

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