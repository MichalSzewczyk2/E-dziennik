package com.example.chatui;

import database.repository.GroupMessageRepository;
import database.repository.GroupNameRepository;
import database.repository.UserRepository;
import database.tables.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AddChat {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField chatName;

    @FXML
    private ListView<String> userList;

    @FXML
    private Label err;

    protected static String cName;

    @FXML
    public void initialize(){
        chatName.setText(AddChat.cName);
        if (Users.getUserList() != null) {
            ArrayList<String> nameList = new ArrayList<>();
            for(Users u: Users.getUserList()){
                String s = u.getName() +" "+ u.getSurname();
                nameList.add(s);
            }
            userList.getItems().addAll(nameList);
        }
    }

    @FXML
    public void goBack(){
        AddChat.cName = "";
        Users.setUserList(null);
        Main m = new Main();
        try{
            m.changeScene("chats.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void search(){
        AddChat.cName = chatName.getText();
        Users user = new UserRepository().getUserByNameAndSurname(name.getText(), surname.getText());
        if(user == null){
            err.setOpacity(1);
        }
        else{
            if(Users.getUserList() == null){
                Users.setUserList(new ArrayList<>());
                Users.addUserToList(user);
            }
            else{
                Users.addUserToList(user);
            }
            reload();
        }
    }

    @FXML
    public void makeChat(){
        new GroupNameRepository().insertChatName(chatName.getText());
        int chatId = new GroupNameRepository().getGroupNameByName(chatName.getText()).getChat_id();
        new GroupMessageRepository().addNewChat(Users.getActiveUser().getUserId(), chatId);
        for (Users u: Users.getUserList()){
            new GroupMessageRepository().addNewChat(u.getUserId(), chatId);
        }
    }

    private void reload(){
        Main m = new Main();
        try{
            m.changeScene("addChat.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
