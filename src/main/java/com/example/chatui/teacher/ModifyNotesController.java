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

public class ModifyNotesController {


    @FXML
    private ChoiceBox<ClassDB> classChoice;

    @FXML
    private ListView<Users> studentList;

    @FXML
    private ListView<Notes> notesList;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea description;

    @FXML
    TextField title;

    @FXML
    private Label classIn;

    private static Users currentStudent;

    private static ClassDB classDB = new ClassDB();

    public static Users getCurrentStudent() {
        return currentStudent;
    }

    public static void setCurrentStudent(Users currentStudent) {
        ModifyNotesController.currentStudent = currentStudent;
    }

    public static ClassDB getClassDB() {
        return classDB;
    }

    public static void setClassDB(ClassDB classDB) {
        ModifyNotesController.classDB = classDB;
    }

    public Notes note;

    @FXML
    public void initialize() {
        ArrayList<ClassDB> classList = new ClassRepository().getClassListWithSupervisorId(Users.getActiveUser().getUserId());
        classChoice.getItems().addAll(classList);
        classChoice.setOnAction(this::setClass);
        if(ModifyNotesController.getClassDB().getName() != null){
            classIn.setText(ModifyNotesController.getClassDB().toString());
            ArrayList<Users> sList = new UserRepository().getClassList(ModifyNotesController.getClassDB().getClass_id());
            studentList.getItems().addAll(sList);
            if(ModifyNotesController.getCurrentStudent()!=null){
                initializeNoteList();
            }
        }

        studentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Users>() {
            @Override
            public void changed(ObservableValue<? extends Users> observable, Users oldValue, Users newValue) {
                ModifyNotesController.setCurrentStudent(studentList.getSelectionModel().getSelectedItem());
                reload();
            }
        });
        notesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Notes>() {
            @Override
            public void changed(ObservableValue<? extends Notes> observable, Notes oldValue, Notes newValue) {
                note = notesList.getSelectionModel().getSelectedItem();
                initializeInfo();
            }
        });

    }

    public void setClass(ActionEvent actionEvent) {
        ModifyNotesController.setClassDB(classChoice.getValue());
        reload();
    }

    public void initializeNoteList(){
        ArrayList<Notes> notes = new NotesRepository().getNotesByStudentAndTeacher(currentStudent.getUserId(), Users.getActiveUser().getUserId());
        notesList.getItems().addAll(notes);
    }

    public void initializeInfo(){
        title.setText(note.getTitle());
        date.setValue(note.getDate().toLocalDate());
        description.setText(note.getMessage());
    }

    @FXML
    public void goBack(ActionEvent event) {
        ModifyNotesController.getClassDB().setName(null);
        new Utilis().goToStarPage();
    }

    @FXML
    public void modify(){
        Notes newNote = new Notes();
        newNote.setNote_id(note.getNote_id());
        newNote.setStudent_id(note.getStudent_id());
        newNote.setTeacher_id(note.getTeacher_id());
        newNote.setTitle(title.getText());
        newNote.setDate(Date.valueOf(date.getValue()));
        newNote.setMessage(description.getText());

        new NotesRepository().modifyNote(newNote);
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText("Zmodyfikowano uwagę!");
        conf.showAndWait();
        reload();
    }

    @FXML
    public void delete(){
        new NotesRepository().deleteNote(note.getNote_id());
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText("Usunięto uwagę!");
        conf.showAndWait();
        reload();
    }


    private void reload(){
        Main m = new Main();
        try{
            m.changeScene("modify_notes.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
