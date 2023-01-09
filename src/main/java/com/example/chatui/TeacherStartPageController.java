package com.example.chatui;

import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class TeacherStartPageController {

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
    public void goSetScore(){
        Main m = new Main();
        try{
            m.changeScene("set_scores.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goModifyScore(){
        Main m = new Main();
        try{
            m.changeScene("modify_score.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goAddNote(){
        Main m = new Main();
        try{
            m.changeScene("set_notes.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goModifyNote(){
        Main m = new Main();
        try{
            m.changeScene("modify_notes.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToChat(){
        Main m = new Main();
        try{
            m.changeScene("chats.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToStatistic(){
        Main m = new Main();
        try{
            m.changeScene("teacher_statistic.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
