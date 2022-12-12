package database.tables;

public class group_name implements DBTable {
    private int chat_id;
    private String name;

    public group_name() {
    }

    public group_name(int chat_id, String name) {
        this.chat_id = chat_id;
        this.name = name;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "group_name{" +
                "chat_id=" + chat_id +
                ", name='" + name + '\'' +
                '}';
    }
}
