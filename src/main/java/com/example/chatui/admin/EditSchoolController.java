package com.example.chatui.admin;

import com.example.chatui.Utilis;
import database.repository.SchoolRepository;
import database.repository.UserRepository;
import database.tables.School;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EditSchoolController {
    @FXML
    private TextField address;

    @FXML
    private TextField name;

    private School school;

    @FXML
    private void initialize(){
        school = new SchoolRepository().getSchool();
        name.setText(school.getName());
        address.setText(school.getAddress());
    }

    @FXML
    void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    @FXML
    void save(ActionEvent event) {
        school.setName(name.getText());
        school.setAddress(address.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(new SchoolRepository().updateSchool(school)){
            alert.setHeaderText("Zmodyfikowano dane szkoły!");
        }else {
            alert.setHeaderText("Nie udało się zmodyfikować danych szkoły!");
        }
        alert.showAndWait();

    }
}
