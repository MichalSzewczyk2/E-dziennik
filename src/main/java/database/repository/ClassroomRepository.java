package database.repository;

import database.client.JDBCClient;
import database.tables.Classroom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassroomRepository {

    Statement statement = new JDBCClient().getStatement();

    public Classroom getClassroomById(int id){
        Classroom classroom = new Classroom();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM classroom WHERE classroom_id = " + id);
            if(rs.next()) {
                classroom.setClassroom_id(rs.getInt("classroom_id"));
                classroom.setNumber(rs.getInt("number"));
                classroom.setFloor(rs.getInt("floor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classroom;
    }
}
