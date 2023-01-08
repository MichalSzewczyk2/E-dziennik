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

    public ArrayList<Grade> getGradesBySubjectandStudent(int subjectId, int userId) {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM grade WHERE student_id = " + userId + " AND subject_id = " + subjectId + ";");
            while (rs.next()) {
                System.out.println(rs.getInt("subject_id"));
                Grade g = new Grade();
                g.setGradeId(rs.getInt("grade_id"));
                g.setStudent_id(rs.getInt("student_id"));
                g.setTask_type_id(rs.getInt("task_type_id"));
                g.setSubject_id(rs.getInt("subject_id"));
                g.setGrade(rs.getInt("grade"));
                g.setDescription(rs.getString("description"));
                g.setImproved_grade_id(rs.getInt("improved_grade_id"));
                g.setDate(rs.getDate("date"));
                grades.add(g);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grades;
    }

    public void addGrade(Grade grade) {
        try {
            statement.executeUpdate("INSERT INTO `grade` (`grade_id`, `subject_id`, `task_type_id`, `student_id`, `grade`, `description`, `improved_grade_id`, `date`) VALUES (NULL, '" +
                    grade.getSubjectId() + "', '" +
                    grade.getTaskTypeId() + "', '" +
                    grade.getStudentId() + "', '" +
                    grade.getGrade() + "', '" +
                    grade.getDescription() + "', NULL, '" +
                    grade.getDate() + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGrade(int gradeId) {
        try {
            statement.executeUpdate("DELETE FROM grade WHERE grade_id = " + gradeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifyGrade(Grade grade) {
        try{
            statement.executeUpdate("UPDATE `grade` SET subject_id = '" + grade.getSubjectId() +
                    "', task_type_id = '"+ grade.getTaskTypeId() +
                    "', student_id = '" + grade.getStudentId() +
                    "', grade = '" + grade.getGrade() +
                    "', description = '" + grade.getDescription() +
                    "', date = '" + grade.getDate() +
                    "' WHERE grade_id = " + grade.getGradeId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}