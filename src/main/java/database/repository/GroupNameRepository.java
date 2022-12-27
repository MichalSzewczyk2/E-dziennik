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
        }
        return groupName;
    }

}
