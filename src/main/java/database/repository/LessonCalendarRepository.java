package database.repository;

import database.client.JDBCClient;
import database.tables.LessonCalendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class LessonCalendarRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<LessonCalendar> getLessonCalendarsByClassId(int id, LocalDate date){
        ArrayList<LessonCalendar> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM lesson_calendar lc JOIN lesson_plan lp ON lc.plan_id = lp.plan_id " +
                    "WHERE lc.date > " + date + " ORDER BY lc.date");
            while(rs.next()) {
                list.add(new LessonCalendar(
                        rs.getInt("lesson_id"),
                        rs.getInt("plan_id"),
                        rs.getDate("date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addLessonCalendar(int planId, LocalDate date) {
        int id = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT MAX(task_type_id) FROM task");
            if (rs.next()) {
                id = rs.getInt("MAX(task_type_id)") + 1;
            }
            statement.executeUpdate("INSERT INTO `lesson_calendar` (`lesson_id`, `plan_id`, `date`) VALUES ('"+
                    id+"', '"+planId+"', '"+date+"');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

}
