package database.tables;

public class task implements DBTable {
    private int task_type_id;
    private int lesson_id;
    private String description;

    public task() {
    }

    public task(int task_type_id, int lesson_id, String description) {
        this.task_type_id = task_type_id;
        this.lesson_id = lesson_id;
        this.description = description;
    }

    public int getTask_type_id() {
        return task_type_id;
    }

    public void setTask_type_id(int task_type_id) {
        this.task_type_id = task_type_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "task{" +
                "task_type_id=" + task_type_id +
                ", lesson_id=" + lesson_id +
                ", description='" + description + '\'' +
                '}';
    }
}
