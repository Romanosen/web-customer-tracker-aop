package com.luv2code.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import  java.sql.*;

//@WebServlet("/TestDbServlet")
public class TestDB extends javax.servlet.http.HttpServlet {


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //setup connection variables

        String name="springstudent";
        String pass="Springstudent1!";

        String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
        String driver="com.mysql.jdbc.Driver";
        //get a connection to the database
        try{
            PrintWriter out =response.getWriter();
            out.println("Connecting to the database "+jdbcUrl);

            //load driver
            Class.forName(driver);

            Connection myConn=DriverManager.getConnection(jdbcUrl,name,pass);

            out.println("Success!");
            myConn.close();

        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new ServletException(ex);
        }


    }
}
