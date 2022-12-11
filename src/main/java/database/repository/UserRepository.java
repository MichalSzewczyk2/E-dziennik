package database.repository;

import database.client.JDBCClient;
import database.tables.Position;
import database.tables.User;

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

    public User getUserById(int id){
        User user = new User();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE user_id = '" + id +"'");
            if(!rs.next())return null;

            user.setUserId(id);
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setMail(rs.getString("mail"));
            user.setPhone_number(Integer.parseInt(rs.getString("phone_number")));
            String pos = rs.getString("position");
            switch (pos) {
                case "admin":
                    user.setPosition(Position.ADMIN);
                    break;
                case "sekretariat":
                    user.setPosition(Position.SEKRETARIAT);
                    break;
                case "nauczyciel":
                    user.setPosition(Position.NAUCZYCIEL);
                    break;
                case "rodzic":
                    user.setPosition(Position.RODZIC);
                    break;
                case "uczen":
                    user.setPosition(Position.UCZEN);
                    break;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }
}
