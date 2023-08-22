package com.aaygee.chatapp.views;

import com.aaygee.chatapp.Network.Client;
import com.aaygee.chatapp.utils.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientChatScreen extends JFrame {
    private JPanel contentPane;
    JScrollPane scrollPane;
    JTextArea textArea;
    JTextField textField;
    private Client client;

    public void sendIt(){
        String message = textField.getText();
        try {
            client.sendMessage(UserInfo.USER_NAME+" - "+message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ClientChatScreen() throws IOException {
        textArea = new JTextArea ();
        client = new Client(textArea);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ;
        setBounds(100, 100, 799, 425);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds (10, 6, 768, 313);
        contentPane.add(scrollPane);

        textArea.setFont(new Font("Lucia Grande",Font.PLAIN,16));
        textArea.setBounds(10, 24, 713, 280);
        scrollPane.getViewport().add(textArea);

        textField = new JTextField();
        textField.setFont(new Font("Lucia Grande",Font.PLAIN,16));
        textField.setBounds(20,339,602,52);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton sendIt = new JButton("Send Message");
        sendIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendIt();
            }
        });
        sendIt.setBounds(627,352,132,29);
        contentPane.add(sendIt);

        setVisible(true);
    }

}
