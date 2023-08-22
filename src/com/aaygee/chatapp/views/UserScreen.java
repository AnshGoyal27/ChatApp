package com.aaygee.chatapp.views;

import com.aaygee.chatapp.dao.UserDAO;
import com.aaygee.chatapp.dto.UserDTO;
import com.aaygee.chatapp.utils.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserScreen extends JFrame {
    JLabel lb1NewLabel;
    Container container;
    JTextField useridtxt;
    JLabel pwdlbl;
    JLabel useridlbl;
    JPasswordField passwordField;
    JButton registerbt;
    JButton loginbt;



    public UserScreen(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        setResizable(false);

        setTitle("LOGIN");
        container = this.getContentPane();
        container.setLayout(null);

        lb1NewLabel = new JLabel("Login");
        lb1NewLabel.setFont(new Font("Lucida Grande",Font.BOLD,40));
        lb1NewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lb1NewLabel.setBounds(319,40,175,75);
        container.add(lb1NewLabel);

        useridtxt = new JTextField();
        useridtxt.setBounds (409, 151, 311, 26);
        container.add(useridtxt);
        useridtxt.setColumns (10);

        pwdlbl = new JLabel("Password");
        pwdlbl.setFont(new Font("Lucida Grande",Font.PLAIN,18));
        pwdlbl.setBounds (196, 219, 107, 33);
        container.add(pwdlbl);

        useridlbl = new JLabel("Userid");
        useridlbl.setFont (new Font("Lucida Grande", Font.PLAIN, 18));
        useridlbl.setBounds (196, 151, 107, 33);
        container.add(useridlbl);

        passwordField = new JPasswordField() ;
        passwordField.setBounds (409, 219, 311, 26);
        container.add (passwordField);

        registerbt = new JButton ("Register");
        registerbt.setFont ( new Font ("Lucida Grande", Font.PLAIN, 16));
        registerbt.setBounds (436, 324, 132, 41);
        container.add(registerbt);
        registerbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        loginbt = new JButton ("Login");
        loginbt. setFont (new Font ("Lucida Grande", Font.PLAIN, 16)) ;
        loginbt. setBounds (278, 324, 132, 41);
        container.add(loginbt);
        loginbt.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        }));

        setBackground(Color.WHITE);
        setSize(833,440);
        setVisible(true);
    }
    private void doLogin(){
        String userid = useridtxt.getText();
        char[] pass = passwordField.getPassword();
        UserDTO userDTO = new UserDTO(userid,pass);
        UserDAO userDAO = new UserDAO();
        String message;
        try {
            if(userDAO.isLogin(userDTO)){
                message = "Welcome"+userid;
                UserInfo.USER_NAME = userid;
                JOptionPane.showMessageDialog(this,message);
                setVisible(false);
                dispose();
                DashBoard dashBoard = new DashBoard(message);
                dashBoard.setVisible(true);
            }
            else {
                message = "Invalid Credentials";
                JOptionPane.showMessageDialog(this,message);
            }
        } catch (SQLException | NoSuchAlgorithmException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void register(){
        String userid = useridtxt.getText();
        char[] pass = passwordField.getPassword();
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO(userid, pass);
        try {
            int result = userDAO.add(userDTO);
            if(result>0){
                JOptionPane.showMessageDialog(this,"Registered Successfully");
            }
            else {
                JOptionPane.showMessageDialog(this,"Registration failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("DB Error");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Generic Exception");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        UserScreen userScreen = new UserScreen();
    }
}
