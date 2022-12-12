package database.tables;

import java.sql.Date;

public class notes implements DBTable {
    private int note_id;
    private int student_id;
    private int teacher_id;
    private Date date;
    private String title;
    private String message;

    public notes() {
    }

    public notes(int note_id, int student_id, int teacher_id, Date date, String title, String message) {
        this.note_id = note_id;
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.date = date;
        this.title = title;
        this.message = message;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "notes{" +
                "note_id=" + note_id +
                ", student_id=" + student_id +
                ", teacher_id=" + teacher_id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
