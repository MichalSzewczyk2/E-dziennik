package com.example.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class EventsControler {


    @FXML
    private Button back;

    @FXML
    void goBack(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("start_page.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
