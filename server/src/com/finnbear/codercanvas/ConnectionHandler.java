package com.finnbear.codercanvas;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * © 2017 Finn Bear All Rights Reserved
 */

public class ConnectionHandler implements Runnable {
    private Bitmap _bitmap;

    private Socket _clientSocket;

    public ConnectionHandler(Socket clientSocket, Bitmap bitmap) {
        _bitmap = bitmap;

        _clientSocket = clientSocket;
    }

    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            _clientSocket.setSoTimeout(500);

            inputStream = _clientSocket.getInputStream();
            outputStream = _clientSocket.getOutputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            PrintWriter writer = new PrintWriter(outputStream);

            String queryString = reader.readLine();

            String[] query = queryString.split(" ");

            switch (query[0]) {
                case "PING":
                    writer.println("OK");
                    break;
                case "GET":
                    if (query.length == 3) {
                        int x = 0;
                        int y = 0;

                        try {
                            x = Integer.parseInt(query[1]);
                            y = Integer.parseInt(query[2]);

                            if (_bitmap.checkPixel(x, y)) {
                                writer.write("OK " + x + " " + y + " " + _bitmap.getPixel(x, y) + "\n");
                            } else {
                                writer.write("ERROR PARAMETER_BOUND\n");
                            }
                        } catch (NumberFormatException e) {
                            writer.write("ERROR PARAMETER_INVALID\n");
                        }
                    } else {
                        writer.write("ERROR PARAMETER_COUNT\n");
                    }
                    break;
                case "SET":
                    if (query.length == 4) {
                        int x = 0;
                        int y = 0;
                        int rgb = 0;

                        try {
                            x = Integer.parseInt(query[1]);
                            y = Integer.parseInt(query[2]);
                            rgb = Integer.parseInt(query[3]);

                            if (_bitmap.checkPixel(x, y)) {
                                _bitmap.setPixel(x, y, rgb);
                                writer.write("OK\n");
                            } else {
                                writer.write("ERROR PARAMETER_BOUND\n");
                            }
                        } catch (NumberFormatException e) {
                            writer.write("ERROR PARAMETER_INVALID\n");
                        }
                    } else {
                        writer.write("ERROR PARAMETER_COUNT\n");
                    }
                    break;
                default:
                    writer.write("ERROR INVALID_REQUEST\n");
            }

            writer.flush();

            reader.close();
            inputStreamReader.close();
            writer.close();

            System.out.println("ConnectionHandler - Request processed.");
        } catch (SocketTimeoutException e) {
            System.out.println("ConnectionHandler - Request failed.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}