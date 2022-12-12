package com.example.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import database.repository.UserRepository;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class LogIn {

    public LogIn(){

    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView xmarkLogin;
    @FXML
    private ImageView xmarkPassword;

    @FXML
    void pressEnter(KeyEvent event){
        try{
            if(event.getCode() == KeyCode.ENTER) checkLogin();
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
    public void userLogin(ActionEvent event) {
        try{
            checkLogin();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void checkLogin() throws IOException {
        Main m = new Main();

        String login = username.getText();
        String pass = password.getText();

        int authResult = new UserRepository().checkIfUserWithCredentialsExists(login, pass);

        switch (authResult) {
            case 0:
                m.changeScene("start_page.fxml",1280,720);
                break;
            case 1:
                xmarkLogin.setOpacity(0.0);
                xmarkPassword.setOpacity(1.0);
                break;
            case 2:
                xmarkLogin.setOpacity(1.0);
                xmarkPassword.setOpacity(1.0);
                break;
            default:
                break;
        }
    }
}
