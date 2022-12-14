package database.repository;

import database.client.JDBCClient;
import database.tables.GroupMessage;
import database.tables.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupMessageRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<GroupMessage> getGroupMessagesByUserId(int id){

        ArrayList<GroupMessage> group = new ArrayList<>();

        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM group_message WHERE user_id = " + id);
            while (rs.next()){
                group.add( new GroupMessage(rs.getInt("chat_id"),
                        rs.getInt("user_id"),
                        rs.getInt("last_read"),
                        new GroupMessage().getEnumFromDB(rs.getString("status"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public ArrayList<Users> getUsersByChatId(int id) {
        ArrayList<Users> users = new ArrayList<>();
        try{
            ResultSet rs = statement.executeQuery("SELECT user_id FROM group_message WHERE chat_id = " + id);
            while(rs.next()) {
                users.add(new UserRepository().getUserById(rs.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void setLastRead(int chatId, int userId, int lastRead) {
        try{
            statement.executeUpdate("Update `group_message` SET last_read = " + lastRead +
                    " WHERE chat_id = " + chatId + " AND user_id = " + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewChat(int  userId, int chatId){
        try{
            statement.executeUpdate("INSERT INTO `group_message` (`chat_id`, `user_id`, `last_read`, `status`) " +
                    "VALUES ('"+chatId+"', '"+userId+"', NULL, NULL);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
