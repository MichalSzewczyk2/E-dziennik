package database.repository;

import database.client.JDBCClient;
import database.tables.position_user;
import database.tables.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {

    Statement statement = new JDBCClient().getStatement();

    public int checkIfUserWithCredentialsExists(String username, String password){
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = '" + username +"'");
            if(rs.next()){
                if(rs.getString("login").equals(username) && rs.getString("password").equals(password))return 0;
                if(rs.getString("login").equals(username) && !rs.getString("password").equals(password))return 1;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return 2;
    }

    public users getUserById(int id){
        users users = new users();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE user_id = '" + id +"'");
            if(!rs.next())return null;

            users.setUser_id(id);
            users.setLogin(rs.getString("login"));
            users.setPassword(rs.getString("password"));
            users.setName(rs.getString("name"));
            users.setSurname(rs.getString("surname"));
            users.setMail(rs.getString("mail"));
            users.setPhone_number(Integer.parseInt(rs.getString("phone_number")));
            String pos = rs.getString("position");
            switch (pos) {
                case "admin":
                    users.setPosition(position_user.ADMIN);
                    break;
                case "sekretariat":
                    users.setPosition(position_user.SEKRETARIAT);
                    break;
                case "nauczyciel":
                    users.setPosition(position_user.NAUCZYCIEL);
                    break;
                case "rodzic":
                    users.setPosition(position_user.RODZIC);
                    break;
                case "uczen":
                    users.setPosition(position_user.UCZEN);
                    break;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return users;
    }
}
