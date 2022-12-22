package database.repository;

import database.client.JDBCClient;
import database.tables.School;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchoolRepository {

    Statement statement = new JDBCClient().getStatement();

    public School getSchool(){
        School school = new School();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM school WHERE school_id = 1");
            if(rs.next()){
                school.setId(rs.getInt("school_id"));
                school.setName(rs.getString("name"));
                school.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return school;
    }

    public boolean updateSchool(School school){
        try{
            statement.executeUpdate("UPDATE school SET name = '" + school.getName() +
                    "', address = '" + school.getAddress() +
                    "' WHERE school_id = 1");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

}
