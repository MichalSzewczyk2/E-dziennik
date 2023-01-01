package com.example.chatui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class addSingleChat {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    public void goBack(){
        Main m = new Main();
        try{
            m.changeScene("chats.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void search(){

    }
}
