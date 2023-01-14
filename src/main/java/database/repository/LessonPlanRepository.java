package database.repository;

import database.client.JDBCClient;
import database.tables.LessonPlan;
import database.tables.WeekDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

public class LessonPlanRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<LessonPlan> getLessonsByClassAndDay(int classId, String day) {

        ArrayList<LessonPlan> planList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("Select * FROM lesson_plan WHERE class_id = " + classId + " AND week_day = '" + day + "' ORDER BY start");
            while (rs.next()) {
                planList.add(new LessonPlan(
                        rs.getInt("plan_id"),
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getInt("teacher_id"),
                        rs.getInt("classroom_id"),
                        new LessonPlan().setWeekDay(rs.getString("week_day")),
                        rs.getTime("start"),
                        rs.getTime("end"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planList;
    }

    public ArrayList<Integer> getSubjectIdByClassAndTeacher(int classId, int teacherId) {

        ArrayList<Integer> subjectList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("Select DISTINCT subject_id FROM lesson_plan WHERE class_id = " +
                    classId + " AND teacher_id = " + teacherId );
            while (rs.next()) {
                subjectList.add(rs.getInt("subject_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectList;
    }


}
