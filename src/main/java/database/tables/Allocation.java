package database.tables;

public class Allocation implements DBTable {
    private int class_id;
    private int student_id;

    public Allocation() {
    }

    public Allocation(int class_id, int student_id) {
        this.class_id = class_id;
        this.student_id = student_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "allocation{" +
                "class_id=" + class_id +
                ", student_id=" + student_id +
                '}';
    }
}
