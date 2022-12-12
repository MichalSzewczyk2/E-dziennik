package database.tables;

public class presence implements DBTable {
    private int student_id;
    private int lesson_id;
    private state_presence state;

    public presence() {
    }

    public presence(int student_id, int lesson_id, state_presence state) {
        this.student_id = student_id;
        this.lesson_id = lesson_id;
        this.state = state;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public state_presence getState() {
        return state;
    }

    public void setState(state_presence state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "presence{" +
                "student_id=" + student_id +
                ", lesson_id=" + lesson_id +
                ", state=" + state +
                '}';
    }
}
