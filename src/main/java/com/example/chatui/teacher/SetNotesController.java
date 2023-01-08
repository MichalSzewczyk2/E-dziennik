package com.example.chatui.teacher;

import com.example.chatui.Main;
import com.example.chatui.Utilis;
import database.repository.*;
import database.tables.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class SetNotesController {


    @FXML
    private ChoiceBox<ClassDB> classChoice;

    @FXML
    private ListView<Users> studentList;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea description;

    @FXML
    private Label classIn;

    private Users currentStudent;

    private static ClassDB classDB = new ClassDB();

    public static ClassDB getClassDB() {
        return classDB;
    }

    public static void setClassDB(ClassDB classDB) {
        SetNotesController.classDB = classDB;
    }

    @FXML
    public void initialize() {
        ArrayList<ClassDB> classList = new ClassRepository().getClassListWithSupervisorId(Users.getActiveUser().getUserId());
        classChoice.getItems().addAll(classList);
        classChoice.setOnAction(this::setClass);
        if(SetNotesController.getClassDB().getName() != null){
            classIn.setText(SetNotesController.getClassDB().toString());
            ArrayList<Users> sList = new UserRepository().getClassList(SetNotesController.getClassDB().getClass_id());
            studentList.getItems().addAll(sList);
        }
        studentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Users>() {
            @Override
            public void changed(ObservableValue<? extends Users> observable, Users oldValue, Users newValue) {
                currentStudent = studentList.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void setClass(ActionEvent actionEvent) {
        SetNotesController.setClassDB(classChoice.getValue());
        reload();
    }

    @FXML
    public void goBack(ActionEvent event) {
        SetNotesController.getClassDB().setName(null);
        new Utilis().goToStarPage();
    }

    @FXML
    public void addNote(){
        Notes notes = new Notes();
        notes.setTitle(title.getText());
        notes.setMessage(description.getText());
        notes.setDate(Date.valueOf(date.getValue()));
        notes.setStudent_id(currentStudent.getUserId());
        notes.setTeacher_id(Users.getActiveUser().getUserId());
        new NotesRepository().addNote(notes);

        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText("Dodano uwagę!");
        conf.showAndWait();
    }

    private void reload(){
        Main m = new Main();
        try{
            m.changeScene("set_Notes.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
