package database.repository;

import database.client.JDBCClient;
import database.tables.position_user;
import database.tables.Users;

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

    public Users getUserByLoginAndPassword(String username, String password){
        Users users = new Users();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = '" + username +"' AND password = '" + password +"'");
            if(!rs.next())return null;

            users.setUser_id(rs.getInt("user_id"));
            users.setLogin(rs.getString("login"));
            users.setPassword(rs.getString("password"));
            users.setName(rs.getString("name"));
            users.setSurname(rs.getString("surname"));
            users.setMail(rs.getString("mail"));
            users.setPhoneNumber(rs.getInt("phone_number"));
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

    public Users getUserByNameAndSurname(String name, String surname){
        Users users = new Users();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE name = '" + name +"' AND surname = '" + surname +"'");
            if(!rs.next())return null;

            users.setUser_id(rs.getInt("user_id"));
            users.setLogin(rs.getString("login"));
            users.setPassword(rs.getString("password"));
            users.setName(rs.getString("name"));
            users.setSurname(rs.getString("surname"));
            users.setMail(rs.getString("mail"));
            users.setPhoneNumber(rs.getInt("phone_number"));
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

    public Users getUserById(int id){
        Users users = new Users();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE user_id = '" + id +"'");
            if(!rs.next())return null;

            users.setUser_id(id);
            users.setLogin(rs.getString("login"));
            users.setPassword(rs.getString("password"));
            users.setName(rs.getString("name"));
            users.setSurname(rs.getString("surname"));
            users.setMail(rs.getString("mail"));
            users.setPhoneNumber(rs.getInt("phone_number"));
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

    public boolean deleteUserByNameAndSurname(String name, String surname){
        try{
            statement.executeUpdate( "DELETE FROM users WHERE name = '" + name + "' AND surname = '" + surname +"'");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean updateUser(Users user){
        try{
            statement.executeUpdate("Update `users` SET name = '" + user.getName() +
                    "', surname =  '" + user.getSurname() +
                    "', login = '" + user.getLogin() +
                    "', password = '" + user.getPassword() +
                    "', mail = '" + user.getMail() +
                    "', phone_number = '" + user.getPhoneNumber() +
                    "', position = '" + user.getPosition().toString().toLowerCase() +
                    "' WHERE user_id = " + user.getUserId());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean addUser(Users user){
        try{
            statement.executeUpdate("INSERT INTO `users` (`user_id`, `login`, `password`, `name`, `surname`, `mail`, `phone_number`, `position`) VALUES (NULL, '" +
                    user.getLogin() + "', '" +
                    user.getPassword() + "', '" +
                    user.getName() + "', '" +
                    user.getSurname() + "', '" +
                    user.getMail() + "', " +
                    user.getPhoneNumber() + ", '" +
                    user.getPosition().toString().toLowerCase() + "')");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }
}
