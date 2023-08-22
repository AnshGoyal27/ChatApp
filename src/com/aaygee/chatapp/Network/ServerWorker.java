package com.aaygee.chatapp.Network;

import java.io.*;
import java.net.Socket;

public class ServerWorker extends Thread{
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;
    private Server server;
    public ServerWorker(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
    }
    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while (true){
            try {
                line = br.readLine();
                if(line.equalsIgnoreCase("quit")){
                    break;
                }
//                out.write(line.getBytes());
                for (ServerWorker serverWorker : server.workers){
                    line=line+"\n";
                    serverWorker.out.write(line.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(br!=null){
                        br.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                    if(out!=null){
                        out.close();
                    }
                    if(clientSocket!=null){
                        clientSocket.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
