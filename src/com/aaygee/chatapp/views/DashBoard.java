package com.aaygee.chatapp.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class DashBoard extends JFrame{
    JPanel contentPane;
    public DashBoard(String message) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE); ;
        setBounds (100,100, 1231, 675);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout (new BorderLayout(0, 0));
        setContentPane(contentPane);
        setTitle(message) ;
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        lblNewLabel.setIcon(new javax.swing.ImageIcon("Images/Chatimg.jpeg"));
        contentPane.add (lblNewLabel,BorderLayout.CENTER) ;
        try {
            new ClientChatScreen();
            setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
