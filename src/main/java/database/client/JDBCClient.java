package database.client;

import java.sql.*;

public class JDBCClient {

    public Statement getStatement(){
        Statement statement;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-dziennik","root", "MYsql123");
             statement = conn.createStatement();
        }catch(SQLException e){
            throw new RuntimeException("Cannot connect to database!");
        }
        return statement;
    }

}
