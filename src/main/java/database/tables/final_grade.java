package database.tables;

public class final_grade implements DBTable {
    private int grade_id;
    private int subject_id;
    private int class_id;
    private int student_id;
    private int grade;
    private type_final_grade type;
    private status_final_grade status;

    public final_grade() {
    }

    public final_grade(int grade_id, int subject_id, int class_id, int student_id, int grade, type_final_grade type, status_final_grade status) {
        this.grade_id = grade_id;
        this.subject_id = subject_id;
        this.class_id = class_id;
        this.student_id = student_id;
        this.grade = grade;
        this.type = type;
        this.status = status;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public type_final_grade getType() {
        return type;
    }

    public void setType(type_final_grade type) {
        this.type = type;
    }

    public status_final_grade getStatus() {
        return status;
    }

    public void setStatus(status_final_grade status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "final_grade{" +
                "grade_id=" + grade_id +
                ", subject_id=" + subject_id +
                ", class_id=" + class_id +
                ", student_id=" + student_id +
                ", grade=" + grade +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
