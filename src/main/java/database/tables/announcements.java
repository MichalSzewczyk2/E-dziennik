package database.tables;

import java.sql.Date;

public class announcements implements DBTable {
    private int announcements_id;
    private String title;
    private String message;
    private Date start;
    private Date end;

    public announcements() {
    }

    public announcements(int announcements_id, String title, String message, Date start, Date end) {
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

    @Override
    public String toString() {
        return "announcements{" +
                "announcements_id=" + announcements_id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
