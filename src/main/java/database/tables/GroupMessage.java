package database.tables;

public class GroupMessage implements DBTable {
    private int chat_id;
    private int user_id;
    private int last_read;
    private StatusGroupMessage status;

    public GroupMessage() {
    }

    public GroupMessage(int chat_id, int user_id, int last_read, StatusGroupMessage status) {
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

    public StatusGroupMessage getStatus() {
        return status;
    }

    public void setStatus(StatusGroupMessage status) {
        this.status = status;
    }

    public StatusGroupMessage getEnumFromDB(String s){
        switch (s){
            case "admin":
                return StatusGroupMessage.ADMIN;
            case "użytkownik":
                return StatusGroupMessage.UZYTKOWNIK;
            default:
                return null;
        }
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
