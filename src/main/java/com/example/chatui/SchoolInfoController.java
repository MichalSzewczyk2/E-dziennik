package com.example.chatui;

import database.repository.SchoolRepository;
import database.tables.School;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SchoolInfoController {

    @FXML
    private TextField address;

    @FXML
    private TextField name;

    private School school;

    @FXML
    public void initialize() {
        school = new SchoolRepository().getSchool();
        name.setText(school.getName());
        address.setText(school.getAddress());
    }

    @FXML
    public void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

}
