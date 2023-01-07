package database.repository;

import database.client.JDBCClient;
import database.tables.Grade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GradeRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<String> getStudentsSubjectsNames(int studentId) {

        ArrayList<String> subjects = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT s.name, s.subject_id FROM grade g JOIN subject s ON g.subject_id = s.subject_id WHERE g.student_id = " + studentId
                    + " GROUP BY g.subject_id");
            while (rs.next()) {
                String grades = new GradeRepository().getGradesByUserAndSubject(studentId, rs.getInt("s.subject_id"));
                subjects.add(rs.getString("s.name") + ": " + grades);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return subjects;
    }

    public String getGradesByUserAndSubject(int userId, int subjectId) {
        String grades;
        try {
            ArrayList<String> g = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT g.grade FROM grade g JOIN subject s ON g.subject_id = s.subject_id WHERE g.student_id = " + userId + " AND s.subject_id = " + subjectId);
            while (rs.next()) {
                g.add(rs.getString("g.grade"));
            }
            grades = g.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return grades;
    }

    public void addGrade(Grade grade) {
        try {
            statement.executeUpdate("INSERT INTO `grade` (`grade_id`, `subject_id`, `task_type_id`, `student_id`, `grade`, `description`, `improved_grade_id`, `date`) VALUES (NULL, '"+
                    grade.getSubjectId() + "', '"+
                    grade.getTaskTypeId() + "', '"+
                    grade.getStudentId() +"', '"+
                    grade.getGrade() + "', '"+
                    grade.getDescription() + "', NULL, '"+
                    grade.getDate() + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
