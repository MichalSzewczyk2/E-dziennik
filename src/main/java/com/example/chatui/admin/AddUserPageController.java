package com.example.chatui.admin;

import com.example.chatui.Utilis;
import database.repository.UserRepository;
import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextField name1;

    @FXML
    private TextField surname1;

    @FXML
    public void initialize(){
        positionChoiceBox.getItems().addAll(new Users().getPositionsToADD());
    }

    @FXML
    private void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    @FXML
    private void addUser(ActionEvent event){
        Users user = new Users();
        user.setName(name.getText());
        user.setSurname(surname.getText());
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        user.setMail(mail.getText());
        if(!phoneNumber.getText().equals("")) {
            user.setPhoneNumber(Integer.parseInt(phoneNumber.getText()));
        }
        user.setPositionByString(positionChoiceBox.getValue());
        boolean flag = new UserRepository().addUser(user);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(flag){
            alert.setHeaderText("Dodano użytkownika!");
        }else {
            alert.setHeaderText("Nie udało się dodać użytkonika!");
        }
        alert.showAndWait();
    }

    @FXML
    void deleteUser(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Czy chcesz usunąć użytkownika?");
        if(alert.showAndWait().get() == ButtonType.OK){
            Alert conf = new Alert(Alert.AlertType.INFORMATION);
            if(new UserRepository().deleteUserByNameAndSurname(name1.getText(), surname1.getText())){
                conf.setHeaderText("Usunięto użytkownika!");
            }else{
                conf.setHeaderText("Nie udało sie usunąć użytkownika!");
            }
            conf.showAndWait();
        }

    }

}
