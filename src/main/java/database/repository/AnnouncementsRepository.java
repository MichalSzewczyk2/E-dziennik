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

    public ArrayList<Announcements> getAllAnnouncements() {
        ArrayList<Announcements> result = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM announcements ORDER BY announcement_id");
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

    public boolean addAnnouncement(Announcements an) {
        try{
            int id = 1;
            ResultSet rs = statement.executeQuery("SELECT MAX(announcement_id) FROM announcements");
            if(rs.next()){
                id = rs.getInt("MAX(announcement_id)") + 1;
            }
            statement.executeUpdate("INSERT INTO `announcements` (`announcement_id`, `title`, `message`, `start`, `end`) VALUES (" +
                    id + ", '" +
                    an.getTitle() +"', '" +
                    an.getMessage() + "', '" +
                    an.getStart() + "', '" +
                    an.getEnd() + "')");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean deleteAnnouncement(Announcements an){
        try {
            statement.executeUpdate("DELETE FROM `announcements` WHERE `announcement_id` = " + an.getAnnouncements_id());
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean updateAnnouncement(Announcements an){
        try {
            statement.executeUpdate("UPDATE `announcements` SET " +
                    "title = '" + an.getTitle() + "', " +
                    "message = '" + an.getMessage() + "', " +
                    "start = '" + an.getStart() + "', " +
                    "end = '" + an.getEnd() + "' " +
                    "WHERE announcement_id = " + an.getAnnouncements_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
