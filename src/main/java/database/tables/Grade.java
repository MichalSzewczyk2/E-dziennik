package database.tables;

import database.repository.SubjectRepository;

import java.sql.Date;

public class Grade implements DBTable {
    private int grade_id;
    private int subject_id;
    private int task_type_id;
    private int student_id;
    private int grade;
    private String description;
    private int improved_grade_id;
    private Date date;

    public Grade() {
    }

    public Grade(int grade_id, int subject_id, int task_type_id, int student_id, int grade, String description, int improved_grade_id, Date date) {
        this.grade_id = grade_id;
        this.subject_id = subject_id;
        this.task_type_id = task_type_id;
        this.student_id = student_id;
        this.grade = grade;
        this.description = description;
        this.improved_grade_id = improved_grade_id;
        this.date = date;
    }

    public int getGradeId() {
        return grade_id;
    }

    public void setGradeId(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getSubjectId() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getTaskTypeId() {
        return task_type_id;
    }

    public void setTask_type_id(int task_type_id) {
        this.task_type_id = task_type_id;
    }

    public int getStudentId() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImproved_grade_id() {
        return improved_grade_id;
    }

    public void setImproved_grade_id(int improved_grade_id) {
        this.improved_grade_id = improved_grade_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {

        Subject s = new SubjectRepository().getSubjectById(subject_id);
        return "["+date+"] "+s.getName()+": "+grade;
    }
}
