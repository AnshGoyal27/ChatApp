package com.aaygee.chatapp.Network;

import com.aaygee.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    ServerSocket serverSocket;
    ArrayList<ServerWorker> workers = new ArrayList<>();
    public Server() throws IOException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORT"));
        serverSocket = new ServerSocket(PORT);
        handleClientRequest();
    }
    // Multiple Client
    public void handleClientRequest() throws IOException {
        while (true){
            Socket clientSocket = serverSocket.accept();
            ServerWorker serverWorker = new ServerWorker(clientSocket,this);
            workers.add(serverWorker);
            serverWorker.start();
        }
    }

    /* Single Client
    public Server() throws IOException {
        ServerSocket serverSocket;
        int PORT = Integer.parseInt(ConfigReader.getValue("PORT"));
        serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        byte arr[] = in.readAllBytes();
        String str = new String(arr);
        System.out.println(str);
        in.close();
        socket.close();
    }
    */
}
