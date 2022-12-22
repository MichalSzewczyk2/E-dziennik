package com.example.chatui;

import database.repository.SchoolRepository;
import database.tables.Announcements;
import database.tables.School;
import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class AdminStartPageControler {



    @FXML
    private Circle notificationIcon;

    @FXML
    private Label notificationNumber;

    @FXML
    private Label announcement;

    @FXML
    private Label sName;


    @FXML
    public void initialize(){
        School school = new SchoolRepository().getSchool();
        sName.setText(school.getName());
    }

    @FXML
    public void logOut(ActionEvent event) {
        new Users().setActiveUser(null);
        Main m = new Main();
        try{
            m.changeScene("login_page.fxml",640,480);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void goToAddUser(){
        Main m = new Main();
        try{
            m.changeScene("add_user_page.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void goToSchoolInfo(MouseEvent event){

    }

    @FXML
    public void goToSchool(ActionEvent event){
        Main m = new Main();
        try{
            m.changeScene("edit_school.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
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
    void goToModifyUser(ActionEvent event) {
        Main m = new Main();
        try{
            m.changeScene("modify_user_page.fxml",1280,720);
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
