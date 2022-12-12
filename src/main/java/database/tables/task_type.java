package database.tables;

public class task_type implements DBTable {
    private int task_type_id;
    private String name;
    private int weight;
    private int teacher_id;

    public task_type() {
    }

    public task_type(int task_type_id, String name, int weight, int teacher_id) {
        this.task_type_id = task_type_id;
        this.name = name;
        this.weight = weight;
        this.teacher_id = teacher_id;
    }

    public int getTask_type_id() {
        return task_type_id;
    }

    public void setTask_type_id(int task_type_id) {
        this.task_type_id = task_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "task_type{" +
                "task_type_id=" + task_type_id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", teacher_id=" + teacher_id +
                '}';
    }
}
