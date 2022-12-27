package database.repository;

import database.client.JDBCClient;
import database.tables.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MessagesRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<Messages> getMessagesByChatId(int id){
        ArrayList<Messages> messages = new ArrayList<>();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM messages WHERE chat_id = " + id);
            while (rs.next()){
                messages.add(new Messages(rs.getInt("message_id"),
                        rs.getInt("chat_id"),
                        rs.getInt("user_id"),
                        rs.getDate("date"),
                        rs.getString("message")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
