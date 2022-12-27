package database.repository;

import database.client.JDBCClient;
import database.tables.GroupMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupMessageRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<GroupMessage> getGroupMessageByUserId(int id){

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

}
