package database.repository;

import database.client.JDBCClient;
import database.tables.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MessagesRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<Messages> getMessagesByChatId(int id) {
        ArrayList<Messages> messages = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM messages WHERE chat_id = " + id + " ORDER BY date");
            while (rs.next()) {
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

    public Messages getNewestMessageInChat(int id) {
        Messages mes = new Messages();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM messages  WHERE chat_id = " + id + " AND date = (SELECT MAX(date) FROM messages WHERE chat_id = " + id + " )  ORDER BY message_id DESC limit 1");
            if (rs.next()) {
                mes.setMessage_id(rs.getInt("message_id"));
                mes.setChat_id(rs.getInt("chat_id"));
                mes.setUser_id(rs.getInt("user_id"));
                mes.setDate(rs.getDate("date"));
                mes.setMessage(rs.getString("message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mes;
    }

    public Messages getMessageById(int id) {
        Messages mes = new Messages();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM messages WHERE message_id = " + id);
            if (rs.next()) {
                mes.setMessage_id(rs.getInt("message_id"));
                mes.setChat_id(rs.getInt("chat_id"));
                mes.setUser_id(rs.getInt("user_id"));
                mes.setDate(rs.getDate("date"));
                mes.setMessage(rs.getString("message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mes;
    }

    public void addMessage(Messages mes) {
        try {
            int id = 1;
            ResultSet rs = statement.executeQuery("SELECT MAX(message_id) FROM messages");
            if (rs.next()) {
                id = rs.getInt("MAX(message_id)") + 1;
            }
            statement.executeUpdate("INSERT INTO `messages` (`message_id`, `chat_id`, `user_id`, `date`, `message`) VALUES ('" +
                    id + "', '" +
                    mes.getChat_id() + "', '" +
                    mes.getUser_id() + "', '" +
                    mes.getDate() + "', '" +
                    mes.getMessage() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
