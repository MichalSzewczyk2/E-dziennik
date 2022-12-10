module UI {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.chatui;
    opens com.example.chatui to javafx.fxml;
}