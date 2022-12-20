package database.repository;

import database.client.JDBCClient;
import database.tables.Announcements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;


public class AnnouncementsRepository {

    Statement statement = new JDBCClient().getStatement();


    public ArrayList<Announcements> getAnnouncementsByActiveDate(LocalDate date) {
        ArrayList<Announcements> result = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM announcements WHERE start < '" + date + "' AND end > '" + date + "' ORDER BY start");
            rs.next();
            while (rs.next()) {
                result.add(new Announcements(
                        rs.getInt("announcement_id"),
                        rs.getString("title"),
                        rs.getString("message"),
                        rs.getDate("start"),
                        rs.getDate("end")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }

        return result;
    }
}
