package database.tables;

public class substitutions implements DBTable {
    private int sub_teacher_id;
    private int lesson_id;

    public substitutions() {
    }

    public substitutions(int sub_teacher_id, int lesson_id) {
        this.sub_teacher_id = sub_teacher_id;
        this.lesson_id = lesson_id;
    }

    public int getSub_teacher_id() {
        return sub_teacher_id;
    }

    public void setSub_teacher_id(int sub_teacher_id) {
        this.sub_teacher_id = sub_teacher_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    @Override
    public String toString() {
        return "substitutions{" +
                "sub_teacher_id=" + sub_teacher_id +
                ", lesson_id=" + lesson_id +
                '}';
    }
}
