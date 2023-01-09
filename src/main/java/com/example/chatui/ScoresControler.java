package com.example.chatui;

import database.repository.GradeRepository;
import database.repository.NotesRepository;
import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
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
    private TextField mean;

    @FXML
    private Label nazwa;

    @FXML
    public void initialize() {
        DecimalFormat df = new DecimalFormat("0.00");
        activeUser = new Users().getActiveUser();
        nazwa.setText(activeUser.getName() + " " + activeUser.getSurname());
        ArrayList<String> subjects = new GradeRepository().getStudentsSubjectsNames(activeUser.getUserId());
        scores.getItems().addAll(subjects);

        ArrayList<String> n = new NotesRepository().getStudentsNotes(activeUser.getUserId());
        notes.getItems().addAll(n);

        double m = new GradeRepository().getStudentMean(Users.getActiveUser().getUserId());
        mean.setText(df.format(m));
    }

    @FXML
    void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

}
