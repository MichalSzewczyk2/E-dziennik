package database.repository;

import database.client.JDBCClient;
import database.tables.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SubjectRepository {

    Statement statement = new JDBCClient().getStatement();

    public Subject getSubjectById(int id){
        Subject subject = new Subject();

        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM subject WHERE subject_id = " + id);
            if(rs.next()){
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

}
