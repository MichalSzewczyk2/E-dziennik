package com.example.chatui.admin;

import com.example.chatui.Utilis;
import database.repository.UserRepository;
import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModifyUserPageController {

    @FXML
    private ChoiceBox<String> positionChoiceBox;
    @FXML
    private Label namel;
    @FXML
    private Label surnamel;
    @FXML
    private Label loginl;
    @FXML
    private Label passwordl;
    @FXML
    private Label maill;
    @FXML
    private Label phoneNumberl;
    @FXML
    private Label positionl;
    @FXML
    private Label userl;

    @FXML
    private Button modify;


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
    private TextField name1;

    @FXML
    private TextField surname1;

    private Users user;

    @FXML
    public void initialize(){
        positionChoiceBox.getItems().addAll(new Users().getPositionsToADD());
    }

    @FXML
    private void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    @FXML
    void modify(ActionEvent event) {

        user.setName(name.getText());
        user.setSurname(surname.getText());
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        user.setMail(mail.getText());
        if(!phoneNumber.getText().equals("")) {
            user.setPhoneNumber(Integer.parseInt(phoneNumber.getText()));
        }
        user.setPositionByString(positionChoiceBox.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(new UserRepository().updateUser(user)){
            alert.setHeaderText("Zmodyfikowano użytkownika!");
        }else {
            alert.setHeaderText("Nie udało się zmodyfikować użytkonika!");
        }
        alert.showAndWait();
    }

    @FXML
    void search(ActionEvent event) {
        user = new UserRepository().getUserByNameAndSurname(name1.getText(), surname1.getText());

        namel.setOpacity(1);
        surnamel.setOpacity(1);
        userl.setOpacity(1);
        modify.setOpacity(1);
        loginl.setOpacity(1);
        passwordl.setOpacity(1);
        maill.setOpacity(1);
        phoneNumberl.setOpacity(1);
        positionl.setOpacity(1);


        name.setOpacity(1);
        name.setText(user.getName());
        surname.setOpacity(1);
        surname.setText(user.getSurname());
        login.setOpacity(1);
        login.setText(user.getLogin());
        password.setOpacity(1);
        password.setText(user.getPassword());
        mail.setOpacity(1);
        mail.setText(user.getMail());
        phoneNumber.setOpacity(1);
        phoneNumber.setText("" + user.getPhoneNumber());
        positionChoiceBox.setOpacity(1);
        positionChoiceBox.setValue(user.getNaturalPosition());
    }

}
