package database.repository;

import database.client.JDBCClient;
import database.tables.GroupName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupNameRepository {

    Statement statement = new JDBCClient().getStatement();

    public GroupName getGroupNameByChatId(int id) {
        GroupName groupName = new GroupName();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM group_name WHERE chat_id = " + id);
            if (rs.next()) {
                groupName.setChat_id(rs.getInt("chat_id"));
                groupName.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return groupName;
    }
    public GroupName getGroupNameByName(String name) {
        GroupName groupName = new GroupName();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM group_name WHERE name = '" + name + "'");
            if (rs.next()) {
                groupName.setChat_id(rs.getInt("chat_id"));
                groupName.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return groupName;
    }

    public int getMaxId(){
        int id = 0;
        try{
            ResultSet rs = statement.executeQuery("SELECT MAX(chat_id) FROM group_name");
            if(rs.next()){
                id = rs.getInt("MAX(chat_id)");
            }
        } catch (SQLException e) {
            return id;
        }
        return id;
    }

    public void insertChatName(String name){
        int id = new GroupNameRepository().getMaxId() + 1;
        try{
            statement.executeUpdate("INSERT INTO `group_name` (`chat_id`, `name`) VALUES ('"+id+"', '"+name+"')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
