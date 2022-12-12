package com.example.chatui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage stage;
    private static int x;
    private static int y;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login_page.fxml")));
        stage.setTitle("apk");
        stage.setScene((new Scene(root, 600, 400)));
        stage.show();
    }

    public void changeScene(String fxml, int v1, int v2) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.setScene(new Scene(pane, v1, v2));
        //stage.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}