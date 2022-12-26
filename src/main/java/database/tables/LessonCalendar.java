package database.tables;

import java.sql.Date;

public class LessonCalendar implements DBTable {
    private int lesson_id;
    private int plan_id;
    private Date date;

    public LessonCalendar() {
    }

    public LessonCalendar(int lesson_id, int plan_id, Date date) {
        this.lesson_id = lesson_id;
        this.plan_id = plan_id;
        this.date = date;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "lesson_calendar{" +
                "lesson_id=" + lesson_id +
                ", plan_id=" + plan_id +
                ", date=" + date +
                '}';
    }
}
