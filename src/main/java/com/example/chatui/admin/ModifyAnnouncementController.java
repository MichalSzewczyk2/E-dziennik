package com.example.chatui.admin;

import database.repository.AnnouncementsRepository;
import database.tables.Announcements;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModifyAnnouncementController {

    @FXML
    private DatePicker end;

    @FXML
    private TextArea message;

    @FXML
    private DatePicker start;

    @FXML
    private TextField title;

    private static Announcements an;

    public void setAnnouncement(Announcements announcement) {
        an = announcement;
    }

    @FXML
    public void initialize(){
        title.setText(an.getTitle());
        message.setText(an.getMessage());
        start.setValue(an.getStart().toLocalDate());
        end.setValue(an.getEnd().toLocalDate());
    }

    @FXML
    public void save(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Czy chcesz zmodyfikowć ogłoszenie?");
        if(alert.showAndWait().get() == ButtonType.OK){
            Alert conf = new Alert(Alert.AlertType.INFORMATION);
            if(new AnnouncementsRepository().updateAnnouncement(an)){
                conf.setHeaderText("Zmodyfikowano ogłoszenie!");
            }else{
                conf.setHeaderText("Nie udało sie zmodyfikować ogłoszenia!");
            }
            conf.showAndWait();
        }

        Stage stage = (Stage) title.getScene().getWindow();
        stage.close();
    }
}
