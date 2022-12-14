package database.repository;

import com.example.chatui.Utilis;
import database.client.JDBCClient;
import database.tables.position_user;
import database.tables.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRepository {

    Statement statement = new JDBCClient().getStatement();

    public int checkIfUserWithCredentialsExists(String username, String password) {
        String eUsername = Utilis.encrypt(username);
        String ePassword = Utilis.encrypt(password);
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = '" +eUsername + "'");
            if (rs.next()) {
                if (rs.getString("login").equals(eUsername) && rs.getString("password").equals(ePassword)) return 0;
                if (rs.getString("login").equals(eUsername) && !rs.getString("password").equals(ePassword)) return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 2;
    }

    public Users getUserByLoginAndPassword(String username, String password) {
        String eUsername = Utilis.encrypt(username);
        String ePassword = Utilis.encrypt(password);
        Users users = new Users();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = '" + eUsername + "' AND password = '" + ePassword + "'");
            if (rs.next()) {

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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Users.decryptUser(users);
    }

    public Users getUserByNameAndSurname(String name, String surname) {
        String eName = Utilis.encrypt(name);
        String eSurname = Utilis.encrypt(surname);
        Users users = new Users();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE name = '" + eName + "' AND surname = '" + eSurname + "'");
            if (rs.next()) {

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
            }
        } catch (SQLException e) {
            return null;
        }
        return Users.decryptUser(users);
    }

    public Users getUserById(int id) {
        Users users = new Users();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE user_id = '" + id + "'");
            if (rs.next()) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Users.decryptUser(users);
    }

    public Users getUserByParentId(int pId) {
        Users users = new Users();
        try {
            ResultSet sr = statement.executeQuery("SELECT child_id FROM parent_children WHERE parent_id = " + pId);
            int id;
            if (sr.next()) {
                id = sr.getInt("child_id");
                users = getUserById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return users;
    }

    public ArrayList<Users> getClassList(int id){
        ArrayList<Users> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users u RIGHT JOIN allocation a " +
                    "ON u.user_id = a.student_id WHERE a.class_id = " + id );
            while (rs.next()){
                Users users = new Users();
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
                list.add(Users.decryptUser(users));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public boolean deleteUserByNameAndSurname(String name, String surname) {
        String eName = Utilis.encrypt(name);
        String eSurname = Utilis.encrypt(surname);
        try {
            statement.executeUpdate("DELETE FROM users WHERE name = '" + eName + "' AND surname = '" + eSurname + "'");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean updateUser(Users users) {
        Users user = Users.encryptUser(users);
        try {
            statement.executeUpdate("Update `users` SET name = '" + user.getName() +
                    "', surname =  '" + user.getSurname() +
                    "', login = '" + user.getLogin() +
                    "', password = '" + user.getPassword() +
                    "', mail = '" + user.getMail() +
                    "', phone_number = '" + user.getPhoneNumber() +
                    "', position = '" + user.getPosition().toString().toLowerCase() +
                    "' WHERE user_id = " + user.getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean addUser(Users users) {
        Users user = Users.encryptUser(users);
        try {
            statement.executeUpdate("INSERT INTO `users` (`user_id`, `login`, `password`, `name`, `surname`, `mail`, `phone_number`, `position`) VALUES (NULL, '" +
                    user.getLogin() + "', '" +
                    user.getPassword() + "', '" +
                    user.getName() + "', '" +
                    user.getSurname() + "', '" +
                    user.getMail() + "', '" +
                    user.getPhoneNumber() + "', '" +
                    user.getPosition().toString().toLowerCase() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
