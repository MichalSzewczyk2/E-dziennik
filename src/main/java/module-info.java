module com.example.chatui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatui to javafx.fxml;
    exports com.example.chatui;
}