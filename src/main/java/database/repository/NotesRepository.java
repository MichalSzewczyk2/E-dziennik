package database.repository;

import database.client.JDBCClient;
import database.tables.Notes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NotesRepository {

    Statement statement = new JDBCClient().getStatement();

    public ArrayList<String> getStudentsNotes(int studentId){
        ArrayList<String> result = new ArrayList<String>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM notes WHERE student_id = " + studentId);
            while (rs.next()) {
                String note = "["+rs.getString("date")+ "]: " + rs.getString("title")
                        + "\n" + rs.getString("message");
                result.add(note);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    public void addNote(Notes note){
        try{
            statement.executeUpdate("INSERT INTO `notes` (`note_id`, `student_id`, `teacher_id`, `date`, `title`, `message`) VALUES (NULL, '"+
                    note.getStudent_id() + "', '"+
                    note.getTeacher_id() + "', '"+
                    note.getDate() + "', '"+
                    note.getTitle() + "', '"+
                    note.getMessage() +"');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
