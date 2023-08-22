package com.aaygee.chatapp.dao;

import com.aaygee.chatapp.dto.UserDTO;
import com.aaygee.chatapp.utils.Encryption;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDAO {
    public int add(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        Connection connection = null ;
        Statement stmt = null;
        int record = 0;
        try{
            connection = CommonDAO.createConnection();
            stmt = connection.createStatement();
            record = stmt.executeUpdate("insert into users (userid,password) values ('"+userDTO.getUserid()+"','"+ Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt!=null){
                stmt.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
        return record;
    }

    public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        String SQL = "select userid from users where userid=? and password=?";
        try {
            con = CommonDAO.createConnection();
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1,userDTO.getUserid());
            pstmt.setString(2,Encryption.passwordEncrypt(new String(userDTO.getPassword())));
            rs = pstmt.executeQuery();
            return rs.next();
        }
        finally {
            if(pstmt!=null){
                pstmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }
}
