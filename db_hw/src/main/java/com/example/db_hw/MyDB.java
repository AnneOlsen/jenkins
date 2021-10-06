package com.example.db_hw;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDB {
    String username = "root";
    String password = "Tal12345";
    String url = "jdbc:mysql://54.221.123.170:3306/";
    //    String url = "jdbc:mysql://1.2.3.4:3306/?useSSL=false";
    String schemaName = "mydb";
    String tableName = "owners";
    List<String> owners = new ArrayList();

    public MyDB() {
        connectAndQuery();
    }

    private void connectAndQuery() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            if (!conn.isClosed()) {
                System.out.println("DB Conn ok ");
                initializeDatabase(conn);
//                // Get the rows:
                String sql = "SELECT * FROM " + tableName;
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();
                owners.clear();
                while (resultSet.next()) {
                    String firstName = resultSet.getString("name");
                    System.out.println("Name: " + firstName);
                    owners.add(firstName);
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private void initializeDatabase(Connection conn) throws Exception {
        // create database
        String query = "create schema if not exists " + schemaName;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.execute("USE " + schemaName);
        // make sure there exists a table named owners, if not create one
        query = "create table if not exists owners(idpersons INT AUTO_INCREMENT, name VARCHAR(45), PRIMARY key(idpersons))";
        stmt.executeUpdate(query);
        // 3. If there was no table named owners, then insert two rows into the new table: "Anna" and "Bent"
        query = "insert IGNORE into " + tableName + " values (1, 'Anna')";
        stmt.execute(query);

        query = "insert IGNORE into " + tableName + " values (2, 'Bent')";
        stmt.execute(query);


    }

    public List<String> getPersons() {
        return owners;
    }

}
