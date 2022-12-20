package com.example.chatui;

import database.tables.Announcements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;


public class StartPage {



    @FXML
    private Circle notificationIcon;

    @FXML
    private Label notificationNumber;

    @FXML
    private Label announcement;


    @FXML
    public void initialize(){
        Announcements a = new Announcements().getAnnouncementByActiveDate();
        announcement.setText(a.getMessage());
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
        Main m = new Main();
        try{
            m.changeScene("chats.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToIncominEvents(ActionEvent event) {
        Main m = new Main();
        try{
            m.changeScene("events.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToMarks(ActionEvent event) {
        Main m = new Main();
        try{
            m.changeScene("scores.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToPlan(ActionEvent event) {
        Main m = new Main();
        try{
            m.changeScene("plans.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void seeNotification(MouseEvent event) {

    }
}
