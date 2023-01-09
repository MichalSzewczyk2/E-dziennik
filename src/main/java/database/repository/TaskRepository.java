package database.repository;

import database.client.JDBCClient;
import database.tables.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskRepository {

    Statement statement = new JDBCClient().getStatement();

    public Task getTaskByLessonId(int id){
        Task task = new Task();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM task WHERE lesson_id =" + id);
            if(rs.next()) {
                task.setLesson_id(rs.getInt("lesson_id"));
                task.setTask_type_id(rs.getInt("task_type_id"));
                task.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void addTask(int lessonId, int typeId, String description){
        try{
            statement.executeUpdate("INSERT INTO `task` (`task_type_id`, `lesson_id`, `description`) VALUES ('"+typeId+"', '"+lessonId+"', '"+description+"');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
