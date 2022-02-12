package controller.perpustakaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static Connection getConnect() throws  SQLException{
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/perpustakaan","root","");
           System.out.println("Connect to db");
           return  connection;
    }

}
