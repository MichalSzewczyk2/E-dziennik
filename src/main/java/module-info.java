module UI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.chatui;
    exports com.example.chatui.admin;
    opens com.example.chatui.admin to javafx.fxml;
    opens com.example.chatui to javafx.fxml;
}