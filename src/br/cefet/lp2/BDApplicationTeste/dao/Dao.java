/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class Dao {

    public Connection getConnection() throws Exception {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bdapli", "root", "");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return conn;
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {

        }
    }

    /*
    public static void main(String[] args) {
        Dao d = new Dao();
        Connection conn = d.getConnection();
        
        System.out.println(conn);
    }
     */
}
