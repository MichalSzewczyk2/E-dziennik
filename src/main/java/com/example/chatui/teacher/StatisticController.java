package com.example.chatui.teacher;

import com.example.chatui.Main;
import com.example.chatui.Utilis;
import database.repository.ClassRepository;
import database.repository.GradeRepository;
import database.repository.UserRepository;
import database.tables.ClassDB;
import database.tables.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatisticController {


    @FXML
    private ChoiceBox<ClassDB> classChoice;

    @FXML
    private ListView<Users> studentList;

    @FXML
    private ListView<String> subjectMeans;

    @FXML
    private TextField classMean;

    @FXML
    private Label classIn;

    private Users currentStudent;

    private static ClassDB classDB = new ClassDB();

    public static ClassDB getClassDB() {
        return classDB;
    }

    public static void setClassDB(ClassDB classDB) {
        StatisticController.classDB = classDB;
    }

    @FXML
    public void initialize() {
        ArrayList<ClassDB> classList = new ClassRepository().getClassListWithSupervisorId(Users.getActiveUser().getUserId());
        classChoice.getItems().addAll(classList);
        classChoice.setOnAction(this::setClass);
        if(StatisticController.getClassDB().getName() != null){
            classIn.setText(StatisticController.getClassDB().toString());
            ArrayList<Users> sList = new UserRepository().getClassList(StatisticController.getClassDB().getClass_id());
            studentList.getItems().addAll(sList);
            initializeClassMean();
        }

        studentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Users>() {
            @Override
            public void changed(ObservableValue<? extends Users> observable, Users oldValue, Users newValue) {
                currentStudent = studentList.getSelectionModel().getSelectedItem();
                initializeMean();
            }
        });
    }

    public void initializeMean() {
        subjectMeans.getItems().addAll(new GradeRepository().getStudentsSubjectsMeans(currentStudent.getUserId()));
    }

    public void initializeClassMean() {
        DecimalFormat df = new DecimalFormat("0.00");
        double mean = 0.0;
        double count = 0.0;
        for(Users student : studentList.getItems()){
            mean += new GradeRepository().getStudentMean(student.getUserId());
            count ++;
        }
        double res = mean / count;
        classMean.setText(df.format(res));
    }

    public void setClass(ActionEvent actionEvent) {
        StatisticController.setClassDB(classChoice.getValue());
        reload();
    }

    @FXML
    public void goBack(ActionEvent event) {
        StatisticController.getClassDB().setName(null);
        new Utilis().goToStarPage();
    }


    private void reload(){
        Main m = new Main();
        try{
            m.changeScene("teacher_statistic.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
