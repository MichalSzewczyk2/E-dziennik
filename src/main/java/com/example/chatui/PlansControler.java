package com.example.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PlansControler {


    @FXML
    private Button back;

    @FXML
    void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

}
