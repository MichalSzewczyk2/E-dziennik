package database.repository;

import database.client.JDBCClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {

    Statement statement = new JDBCClient().getStatement();

    public boolean checkIfUserWithCredentialsExists(String username, String password){

        try{
            ResultSet rs = statement.executeQuery("SELECT user_id FROM users WHERE login = '" + username +"' and password = '" + password +"'");
            if(rs.next()) return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
}
