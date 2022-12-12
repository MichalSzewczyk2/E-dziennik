package database.tables;

import java.sql.Time;

public class lesson_plan implements DBTable {
    private int plan_id;
    private int class_id;
    private int subject_id;
    private int teacher_id;
    private int classroom_id;
    private week_day_lesson_plan week_day;
    private Time start;
    private Time end;

    public lesson_plan() {
    }

    public lesson_plan(int plan_id, int class_id, int subject_id, int teacher_id, int classroom_id, week_day_lesson_plan week_day, Time start, Time end) {
        this.plan_id = plan_id;
        this.class_id = class_id;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.classroom_id = classroom_id;
        this.week_day = week_day;
        this.start = start;
        this.end = end;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    public week_day_lesson_plan getWeek_day() {
        return week_day;
    }

    public void setWeek_day(week_day_lesson_plan week_day) {
        this.week_day = week_day;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "lesson_plan{" +
                "plan_id=" + plan_id +
                ", class_id=" + class_id +
                ", subject_id=" + subject_id +
                ", teacher_id=" + teacher_id +
                ", classroom_id=" + classroom_id +
                ", week_day=" + week_day +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
