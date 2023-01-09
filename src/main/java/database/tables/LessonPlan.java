package database.tables;

import database.repository.SubjectRepository;

import java.sql.Time;

public class LessonPlan implements DBTable {
    private int plan_id;
    private int class_id;
    private int subject_id;
    private int teacher_id;
    private int classroom_id;
    private WeekDay week_day;
    private Time start;
    private Time end;

    public LessonPlan() {
    }

    public LessonPlan(int plan_id, int class_id, int subject_id, int teacher_id, int classroom_id, WeekDay week_day, Time start, Time end) {
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

    public WeekDay getWeek_day() {
        return week_day;
    }

    public void setWeek_day(WeekDay week_day) {
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

    public WeekDay setWeekDay(String day){
        switch (day){
            case "poniedziałek":
                return WeekDay.PONIEDZIALEK;
            case "wtorek":
                return WeekDay.WTOREK;
            case "sroda":
                return WeekDay.SRODA;
            case "czwartek":
                return WeekDay.CZWARTEK;
            case "piatek":
                return WeekDay.PIATEK;
            case "sobota":
                return WeekDay.SOBOTA;
            case "niedziela":
                return WeekDay.NIEDZIELA;
            default:
                return null;
        }
    }

    public String getNaturalDay(String day) {
        switch (day) {
            case "PONIEDZIALEK":
                return "Poniedziałek";
            case "WTOREK":
                return "Wtorek";
            case "SRODA":
                return "Środa";
            case "CZWARTEK":
                return "Czwartek";
            case "PIATEK":
                return "Piątek";
            case "SOBOTA":
                return "Sobota";
            case "NIEDZIELA":
                return "Niedziela";
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        Subject subject = new SubjectRepository().getSubjectById(subject_id);
        return subject.getName() + "\n" +
                trimLastCharacters(start.toString(), 3) + " - "
                + trimLastCharacters(end.toString(), 3);
    }

    public static String trimLastCharacters(String input, int i) {
        if (input.length() > i) {
            return input.substring(0, input.length() - i);
        } else {
            return input;
        }
    }
}
