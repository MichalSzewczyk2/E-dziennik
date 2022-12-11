package com.example.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import database.repository.UserRepository;

import java.io.IOException;

public class LogIn {

    public LogIn(){

    }

    @FXML
    private Button loginButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView xmarkLogin;
    @FXML
    private ImageView xmarkPassword;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Main m = new Main();

        String login = username.getText();
        String pass = password.getText();

        if (new UserRepository().checkIfUserWithCredentialsExists(login, pass)) {
            m.changeScene("start_page.fxml");
        }

//        if (username.getText().toString().equals("user") && password.getText().toString().equals("user")){
//            m.changeScene("start_page.fxml");
//        }
//        if(!username.getText().toString().equals("user")){
//            xmarkLogin.setOpacity(1.0);
//        }
//        if(username.getText().toString().equals("user")){
//            xmarkLogin.setOpacity(0.0);
//        }
//        if(password.getText().toString().equals("user")){
//            xmarkPassword.setOpacity(0.0);
//        }
//        if(!password.getText().toString().equals("user")){
//            xmarkPassword.setOpacity(1.0);
//        }
    }
}
