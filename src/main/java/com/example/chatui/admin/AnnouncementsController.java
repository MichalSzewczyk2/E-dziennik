package com.example.chatui.admin;

import com.example.chatui.Main;
import com.example.chatui.Utilis;
import database.repository.AnnouncementsRepository;
import database.repository.UserRepository;
import database.tables.Announcements;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnnouncementsController {
    @FXML
    private DatePicker end;

    @FXML
    private TextArea message;

    @FXML
    private DatePicker start;

    @FXML
    private TextField title;

    @FXML
    private ListView<Announcements> list;

    private Announcements ann;

    @FXML
    public void initialize(){

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
        ArrayList<Announcements> announcements = new AnnouncementsRepository().getAllAnnouncements();
        list.getItems().addAll(announcements);

        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Announcements>() {
            @Override
            public void changed(ObservableValue<? extends Announcements> observableValue, Announcements announcements, Announcements t1){
                ann = list.getSelectionModel().getSelectedItem();
            }
        });
    }

    @FXML
    public void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    @FXML
    public void add(ActionEvent event){
       // ZoneId defaultZoneId = ZoneId.systemDefault();

        Announcements an = new Announcements();
        //Date.from(start.getValue().atStartOfDay(defaultZoneId).toInstant())
        an.setTitle(title.getText());
        an.setMessage(message.getText());
        an.setStart(Date.valueOf(start.getValue()));
        an.setEnd(Date.valueOf(end.getValue()));

        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        if(new AnnouncementsRepository().addAnnouncement(an)){
            conf.setHeaderText("Dodano ogłoszenie!");
        }else{
            conf.setHeaderText("Nie udało się dodać ogłoszenia!");
        }
        conf.showAndWait();

        Main m = new Main();
        try{
            m.changeScene("announcements_page.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Czy chcesz usunąć ogłoszenie?");
        if(alert.showAndWait().get() == ButtonType.OK){
            Alert conf = new Alert(Alert.AlertType.INFORMATION);
            if(new AnnouncementsRepository().deleteAnnouncement(ann)){
                conf.setHeaderText("Usunięto ogłoszenie!");
            }else{
                conf.setHeaderText("Nie udało sie usunąć ogłoszenia!");
            }
            conf.showAndWait();
        }

        Main m = new Main();
        try{
            m.changeScene("announcements_page.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void modify(ActionEvent event) {
        Parent root;
        new ModifyAnnouncementController().setAnnouncement(ann);
        Main m = new Main();
        try{
            m.openNewWindow("modify_ann.fxml",800,600,"modify");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
