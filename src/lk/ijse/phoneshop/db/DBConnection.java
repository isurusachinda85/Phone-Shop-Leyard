package lk.ijse.phoneshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dBConnection;//class eke naminma refarance varible ekak hadanawa
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {//object hadanna ba constructor eka private nisa
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/phoneshop?useSSL=false","root","1234");
    }

    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
        if(dBConnection==null){
            dBConnection=new DBConnection();
        }
        return dBConnection;
    }
}
