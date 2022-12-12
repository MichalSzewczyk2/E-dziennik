package database.tables;

public class class_DB implements DBTable {
    private int class_id;
    private int supervisor_id;
    private int year;
    private String name;

    public class_DB() {
    }

    public class_DB(int class_id, int supervisor_id, int year, String name) {
        this.class_id = class_id;
        this.supervisor_id = supervisor_id;
        this.year = year;
        this.name = name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "class_DB{" +
                "class_id=" + class_id +
                ", supervisor_id=" + supervisor_id +
                ", year=" + year +
                ", name='" + name + '\'' +
                '}';
    }
}
