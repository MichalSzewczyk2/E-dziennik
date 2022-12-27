package database.tables;

import java.sql.Date;

public class Messages implements DBTable {
    private int message_id;
    private int chat_id;
    private int user_id;
    private Date date;
    private String message;

    public Messages() {
    }

    public Messages(int message_id, int chat_id, int user_id, Date date, String message) {
        this.message_id = message_id;
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.date = date;
        this.message = message;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "messages{" +
                "message_id=" + message_id +
                ", chat_id=" + chat_id +
                ", user_id=" + user_id +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
