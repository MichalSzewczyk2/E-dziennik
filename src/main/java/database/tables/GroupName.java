package database.tables;

public class GroupName implements DBTable {
    private static GroupName groupName;

    private int chat_id;
    private String name;

    public GroupName() {
    }

    public GroupName(int chat_id, String name) {
        this.chat_id = chat_id;
        this.name = name;
    }

    public static GroupName getGroupName() {
        return groupName;
    }

    public static void setGroupName(GroupName groupName) {
        GroupName.groupName = groupName;
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
        return name;

    }
}
