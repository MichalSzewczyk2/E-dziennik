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

public class SetScoresController {

    @FXML
    private ChoiceBox<ClassDB> classChoice;

    @FXML
    private ListView<Users> studentList;

    @FXML
    private ChoiceBox<TaskType> type;

    @FXML
    private ChoiceBox<Subject> subject;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea description;

    @FXML TextField mark;

    @FXML
    private Label classIn;

    private Users currentStudent;

    private static ClassDB classDB = new ClassDB();

    public static ClassDB getClassDB() {
        return classDB;
    }

    public static void setClassDB(ClassDB classDB) {
        SetScoresController.classDB = classDB;
    }

    @FXML
    public void initialize() {
        ArrayList<ClassDB> classList = new ClassRepository().getClassListWithSupervisorId(Users.getActiveUser().getUserId());
        classChoice.getItems().addAll(classList);
        classChoice.setOnAction(this::setClass);
        if(SetScoresController.getClassDB().getName() != null){
            initializeSubjectList();
            classIn.setText(SetScoresController.getClassDB().toString());
            ArrayList<Users> sList = new UserRepository().getClassList(SetScoresController.getClassDB().getClass_id());
            studentList.getItems().addAll(sList);
        }
        type.getItems().addAll(new TaskTypeRepository().getAllTaskTypes());

        studentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Users>() {
            @Override
            public void changed(ObservableValue<? extends Users> observable, Users oldValue, Users newValue) {
                currentStudent = studentList.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void setClass(ActionEvent actionEvent) {
        SetScoresController.setClassDB(classChoice.getValue());
        reload();
    }

    public void initializeSubjectList(){
        ArrayList<Integer> subjectIdList = new LessonPlanRepository().getSubjectIdByClassAndTeacher(
                SetScoresController.classDB.getClass_id(),
                Users.getActiveUser().getUserId());
        ArrayList<Subject> subjectList = new ArrayList<>();
        for(Integer id: subjectIdList){
            subjectList.add(new SubjectRepository().getSubjectById(id));
        }
        subject.getItems().addAll(subjectList);
    }

    @FXML
    public void goBack(ActionEvent event) {
        SetScoresController.getClassDB().setName(null);
        new Utilis().goToStarPage();
    }

    @FXML
    public void addGrade(){
        Grade grade = new Grade();
        grade.setSubject_id(subject.getValue().getSubject_id());
        grade.setTask_type_id(type.getValue().getTask_type_id());
        grade.setStudent_id(currentStudent.getUserId());
        grade.setGrade(Integer.parseInt(mark.getText()));
        grade.setDescription(description.getText());
        grade.setDate(Date.valueOf(date.getValue()));

        new GradeRepository().addGrade(grade);
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText("Dodano ocenę!");
        conf.showAndWait();
    }


    private void reload(){
        Main m = new Main();
        try{
            m.changeScene("set_scores.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
