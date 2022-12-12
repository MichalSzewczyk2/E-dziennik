package database.tables;

public class classroom implements DBTable {
    private int classroom_id;
    private int number;
    private int floor;

    public classroom() {
    }

    public classroom(int classroom_id, int number, int floor) {
        this.classroom_id = classroom_id;
        this.number = number;
        this.floor = floor;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "classroom{" +
                "classroom_id=" + classroom_id +
                ", number=" + number +
                ", floor=" + floor +
                '}';
    }
}
