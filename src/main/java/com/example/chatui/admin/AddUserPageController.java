package com.example.chatui.admin;

import com.example.chatui.Utilis;
import database.repository.UserRepository;
import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddUserPageController {

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> positionChoiceBox;

    @FXML
    private TextField login;

    @FXML
    private TextField mail;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField surname;

    @FXML
    private Button add;

    @FXML
    private Label git;

    @FXML
    public void initialize(){
        positionChoiceBox.getItems().addAll(new Users().getPositionsToADD());
    }

    @FXML
    private void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    @FXML
    private void addUser(){
        Users user = new Users();
        user.setName(name.getText());
        user.setSurname(surname.getText());
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        user.setMail(mail.getText());
        if(phoneNumber.getText() != null) {
            user.setPhone_number(Integer.parseInt(phoneNumber.getText()));
        }
        user.setPositionByString(positionChoiceBox.getValue());

        if(new UserRepository().addUser(user)) {
            git.setOpacity(1);
        }
        else{
            git.setOpacity(1);
            git.setText("nie git");
        }
    }

}
