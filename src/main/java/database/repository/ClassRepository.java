package database.repository;

import database.client.JDBCClient;
import database.tables.ClassDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClassRepository {

    Statement statement = new JDBCClient().getStatement();

    public ClassDB getClassByUser(int userId) {
        ClassDB classDB = new ClassDB();
        try {
            ResultSet rs = statement.executeQuery("SELECT class_id FROM allocation WHERE student_id = " + userId);
            if (rs.next()) {
                classDB.setClass_id(rs.getInt("class_id"));
            } else return null;
            ResultSet sr = statement.executeQuery("SELECT * FROM class WHERE class_id = " + classDB.getClass_id());
            if (sr.next()) {
                classDB.setSupervisor_id(sr.getInt("supervisor_id"));
                classDB.setYear(sr.getInt("year"));
                classDB.setName(sr.getString("name"));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classDB;
    }

    public ArrayList<ClassDB> getAllClassList() {
        ArrayList<ClassDB> classList = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM class");
            while (rs.next()) {
                classList.add(new ClassDB(
                        rs.getInt("class_id"),
                        rs.getInt("supervisor_id"),
                        rs.getInt("year"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classList;
    }

    public ArrayList<ClassDB> getClassListWithSupervisorId(int id) {
        ArrayList<ClassDB> classList = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM class WHERE supervisor_id = " + id);
            while (rs.next()) {
                classList.add(new ClassDB(
                        rs.getInt("class_id"),
                        rs.getInt("supervisor_id"),
                        rs.getInt("year"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classList;
    }
}
