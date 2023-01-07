package database.repository;

import database.client.JDBCClient;
import database.tables.TaskType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskTypeRepository {

    Statement statement = new JDBCClient().getStatement();

    public TaskType getTaskTypeById(int id) {
        TaskType taskType = new TaskType();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM task_type WHERE task_type_id = " + id);
            if (rs.next()) {
                taskType.setTask_type_id(rs.getInt("task_type_id"));
                taskType.setName(rs.getString("name"));
                taskType.setWeight(rs.getInt("weight"));
                taskType.setTeacher_id(rs.getInt("teacher_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskType;
    }

    public ArrayList<TaskType> getAllTaskTypes() {
        ArrayList<TaskType> taskTypes = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM task_type");
            while (rs.next()) {
                TaskType taskType = new TaskType();
                taskType.setTask_type_id(rs.getInt("task_type_id"));
                taskType.setName(rs.getString("name"));
                taskType.setWeight(rs.getInt("weight"));
                taskType.setTeacher_id(rs.getInt("teacher_id"));
                taskTypes.add(taskType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskTypes;
    }
}
