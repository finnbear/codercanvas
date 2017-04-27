package com.finnbear.codercanvas.client;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

import java.io.*;
import java.net.Socket;

public class CanvasClient {
    private final String serverUrl = "www.codercanvas.com";
    private final int serverPort = 4500;

    public CanvasClient() {

    }

    public boolean testConnection() {
        Socket socket = null;

        InputStream inputStream = null;
        OutputStream outputStream = null;

        InputStreamReader inputStreamReader = null;

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            // Create socket
            socket = new Socket(serverUrl, serverPort);

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(inputStream);

            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(outputStream);

            // Ping
            writer.println("PING");
            writer.flush();

            // Check reply
            String response = reader.readLine();

            if (response.equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }

                if (reader != null) {
                    reader.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public int getPixel(int x, int y) {
        Socket socket = null;

        InputStream inputStream = null;
        OutputStream outputStream = null;

        InputStreamReader inputStreamReader = null;

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            // Create socket
            socket = new Socket(serverUrl, serverPort);

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(inputStream);

            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(outputStream);

            // Ping
            writer.println("GET " + x + " " + y + "\n");
            writer.flush();

            // Check reply
            String response = reader.readLine();

            if (response.startsWith("ERROR")) {
                return -1;
            } else {
                String[] responseChunks = response.split(" ");

                return Integer.parseInt(responseChunks[3]);
            }
        } catch (IOException e) {
            return -1;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }

                if (reader != null) {
                    reader.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    public boolean setPixel(int x, int y, int rgb) {
        Socket socket = null;

        InputStream inputStream = null;
        OutputStream outputStream = null;

        InputStreamReader inputStreamReader = null;

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            // Create socket
            socket = new Socket(serverUrl, serverPort);

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(inputStream);

            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(outputStream);

            // Ping
            writer.println("SET " + x + " " + y + " " + rgb + "\n");
            writer.flush();

            // Check reply
            String response = reader.readLine();

            if (response.startsWith("ERROR")) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }

                if (reader != null) {
                    reader.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
