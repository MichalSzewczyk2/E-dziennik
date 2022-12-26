package database.tables;

public class Event {

    private String date;
    private String name;
    private String Description;

    private int plan_id;
    private int lesson_id;
    private int task_type_id;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getTask_type_id() {
        return task_type_id;
    }

    public void setTask_type_id(int task_type_id) {
        this.task_type_id = task_type_id;
    }

    @Override
    public String toString() {
        return "[" + date + "] " + name;
    }
}
