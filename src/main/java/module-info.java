module UI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.chatui;
    opens com.example.chatui to javafx.fxml;
}