package com.aaygee.chatapp.Network;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientWorker extends Thread{
    private InputStream in;
    private JTextArea textArea;
    public ClientWorker(InputStream in, JTextArea textArea){
        this.in = in;
        this.textArea = textArea;
    }
    @Override
    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while (true){
            try {
                line = br.readLine();
                textArea.setText(textArea.getText()+line+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(in!=null){
                        in.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
