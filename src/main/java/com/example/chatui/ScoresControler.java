package com.example.chatui;

import database.repository.GradeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ScoresControler {


    @FXML
    private Button back;

    @FXML
    private ListView<String> scores;


    @FXML
    public void initialize() {
        ArrayList<String> subjects = new GradeRepository().getStudentsSubjectsNames(2);
        scores.getItems().addAll(subjects);
    }

    @FXML
    void goBack(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("start_page.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
