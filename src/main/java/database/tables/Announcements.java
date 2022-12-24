package database.tables;

import database.repository.AnnouncementsRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static java.time.LocalDate.now;

public class Announcements implements DBTable {
    private int announcements_id;
    private String title;
    private String message;
    private Date start;
    private Date end;

    public Announcements() {
    }

    public Announcements(int announcements_id, String title, String message, Date start, Date end) {
        this.announcements_id = announcements_id;
        this.title = title;
        this.message = message;
        this.start = start;
        this.end = end;
    }

    public int getAnnouncements_id() {
        return announcements_id;
    }

    public void setAnnouncements_id(int announcements_id) {
        this.announcements_id = announcements_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Announcements getAnnouncementByActiveDate(){
        LocalDate date =  now();

        ArrayList<Announcements> an = new AnnouncementsRepository().getAnnouncementsByActiveDate(date);

        Date d = an.get(0).getEnd();
        Announcements rs = an.get(0);

        for (Announcements a : an) {
            if(a.getEnd().after(d)){
                d = a.getEnd();
                rs = a;
            }
        }

        return rs;
    }

    @Override
    public String toString() {
        return  title + ":\n" +
                message;
    }
}
