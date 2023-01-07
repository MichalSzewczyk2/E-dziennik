module UI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.chatui;
    exports com.example.chatui.admin;
    exports com.example.chatui.teacher;
    opens com.example.chatui.admin to javafx.fxml;
    opens com.example.chatui.teacher to javafx.fxml;
    opens com.example.chatui to javafx.fxml;
}