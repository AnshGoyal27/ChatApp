package com.aaygee.chatapp.Network;

import com.aaygee.chatapp.utils.ConfigReader;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    OutputStream out;
    InputStream in;
    ClientWorker worker;
    JTextArea textArea;
    public Client(JTextArea textArea) throws IOException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORT"));
            socket = new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            this.textArea = textArea;
            readMessage();
//            Scanner scanner = new Scanner(System.in);
//            String message = scanner.nextLine();
//            OutputStream out =  socket.getOutputStream();
//            out.write(message.getBytes());
//            scanner.close();
//            out.close();
//            socket.close();
    }
    public void sendMessage(String message) throws IOException {
        message = message+ "\n";
        out.write(message.getBytes());
    }
    public void readMessage(){
        worker = new ClientWorker(in,textArea);
        worker.start();
    }
}
