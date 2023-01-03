package com.example.chatui;

import database.repository.AnnouncementsRepository;
import database.repository.SchoolRepository;
import database.tables.Announcements;
import database.tables.School;
import database.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class StartPage {



    @FXML
    private Circle notificationIcon;

    @FXML
    private Label notificationNumber;

    @FXML
    private ListView<Announcements> list;

    @FXML
    private Label sName;

    @FXML
    private Label title;


    @FXML
    public void initialize(){

        if(Users.getActiveParent() != null){
            title.setText("Panel Rodzica");
        }

        System.out.println(Users.getActiveUser());

        list.setCellFactory(new Callback<ListView<Announcements>, ListCell<Announcements>>() {
            @Override
            public ListCell<Announcements> call(ListView<Announcements> announcementsListView) {
                final ListCell cell = new ListCell(){
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty){
                        super.updateItem(item, empty);
                        if(!empty){
                            text = new Text(item.toString());
                            text.setWrappingWidth(list.getPrefWidth()-20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
        ArrayList<Announcements> announcements = new AnnouncementsRepository().getAnnouncementsByActiveDate(LocalDate.now());
        list.getItems().addAll(announcements);
        School school = new SchoolRepository().getSchool();
        sName.setText(school.getName());
        // TODO dodać sprawdzeni czy są powiadomienia i wyświetlanie kropki
        // TODO dodać sprawdzanie i wyświetlanie ogłoszeń przy załadowaniu strony
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
