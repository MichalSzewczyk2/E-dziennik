package database.repository;

import database.client.JDBCClient;
import database.tables.Notes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NotesRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<String> getStudentsNotes(int studentId) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM notes WHERE student_id = " + studentId);
            while (rs.next()) {
                String note = "[" + rs.getString("date") + "]: " + rs.getString("title")
                        + "\n" + rs.getString("message");
                result.add(note);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ArrayList<Notes> getNotesByStudentAndTeacher(int studentId, int teacherId) {
        ArrayList<Notes> result = new ArrayList<Notes>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM notes WHERE student_id = " + studentId
                    + " AND teacher_id = " + teacherId);
            while (rs.next()) {
                Notes notes = new Notes();
                notes.setNote_id(rs.getInt("note_id"));
                notes.setStudent_id(rs.getInt("student_id"));
                notes.setTeacher_id(rs.getInt("teacher_id"));
                notes.setDate(rs.getDate("date"));
                notes.setTitle(rs.getString("title"));
                notes.setMessage(rs.getString("message"));
                result.add(notes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void addNote(Notes note) {
        try {
            statement.executeUpdate("INSERT INTO `notes` (`note_id`, `student_id`, `teacher_id`, `date`, `title`, `message`) VALUES (NULL, '" +
                    note.getStudent_id() + "', '" +
                    note.getTeacher_id() + "', '" +
                    note.getDate() + "', '" +
                    note.getTitle() + "', '" +
                    note.getMessage() + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifyNote(Notes note) {
        try{
            statement.executeUpdate("UPDATE `notes` SET `student_id` = '" + note.getStudent_id() +
                    "', `teacher_id` = '" + note.getTeacher_id() +
                    "', `date` = '" + note.getDate() +
                    "', `title` = '" + note.getTitle() +
                    "', `message` = '" + note.getMessage() +
                    "' WHERE `note_id` = " + note.getNote_id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNote(int noteId) {
        try {
            statement.executeUpdate("DELETE FROM `notes` WHERE `note_id` = " + noteId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
