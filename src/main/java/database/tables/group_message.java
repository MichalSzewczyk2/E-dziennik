package database.tables;

public class group_message implements DBTable {
    private int chat_id;
    private int user_id;
    private int last_read;
    private status_group_message status;

    public group_message() {
    }

    public group_message(int chat_id, int user_id, int last_read, status_group_message status) {
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.last_read = last_read;
        this.status = status;
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

    public int getLast_read() {
        return last_read;
    }

    public void setLast_read(int last_read) {
        this.last_read = last_read;
    }

    public status_group_message getStatus() {
        return status;
    }

    public void setStatus(status_group_message status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "group_message{" +
                "chat_id=" + chat_id +
                ", user_id=" + user_id +
                ", last_read=" + last_read +
                ", status=" + status +
                '}';
    }
}
