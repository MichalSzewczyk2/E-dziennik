package database.repository;

import database.client.JDBCClient;

import java.sql.SQLException;
import java.sql.Statement;

public class ParentChildrenRepository {

    Statement statement = new JDBCClient().getStatement();

    public void addRow(int parentId, int childId) {
        try{
            statement.executeUpdate("insert into parent_children (parent_id, child_id) values ("+parentId+","+childId+")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
