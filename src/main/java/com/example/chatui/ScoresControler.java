package com.example.chatui;

import database.repository.GradeRepository;
import database.repository.NotesRepository;
import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ScoresControler {

    Users activeUser;

    @FXML
    private Button back;

    @FXML
    private ListView<String> scores;

    @FXML
    private ListView<String> notes;

    @FXML
    private Label nazwa;

    @FXML
    public void initialize() {
        activeUser = new Users().getActiveUser();
        nazwa.setText(activeUser.getName() + " " + activeUser.getSurname());
        ArrayList<String> subjects = new GradeRepository().getStudentsSubjectsNames(activeUser.getUser_id());
        scores.getItems().addAll(subjects);

        ArrayList<String> n = new NotesRepository().getStudentsNotes(activeUser.getUser_id());
        notes.getItems().addAll(n);
    }

    @FXML
    void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

}
