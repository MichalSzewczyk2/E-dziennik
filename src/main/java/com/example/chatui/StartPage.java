package com.example.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class StartPage {

    @FXML
    private Circle notificationIcon;

    @FXML
    private Label notificationNumber;

    @FXML
    private Label announcement;


    public StartPage(){
        // TODO dodać sprawdzeni czy są powiadomienia i wyświetlanie kropki
        // TODO dodać sprawdzanie i wyświetlanie ogłoszeń przy załadowaniu strony
    }

    @FXML
    public void logOut(ActionEvent event) {
        Main m = new Main();
        try{
            m.changeScene("login_page.fxml",640,480);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void goToSchoolInfo(MouseEvent event){

    }

    @FXML
    void goToChat(ActionEvent event) {

    }

    @FXML
    void goToIncominEvents(ActionEvent event) {

    }

    @FXML
    void goToMarks(ActionEvent event) {

    }

    @FXML
    void goToPlan(ActionEvent event) {

    }

    @FXML
    void seeNotification(MouseEvent event) {

    }
}
