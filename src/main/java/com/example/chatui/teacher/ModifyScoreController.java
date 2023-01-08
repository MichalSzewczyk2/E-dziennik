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

public class ModifyScoreController {

    @FXML
    private ChoiceBox<ClassDB> classChoice;

    @FXML
    private ListView<Users> studentList;

    @FXML
    private ListView<Grade> scoreList;

    @FXML
    private ChoiceBox<TaskType> type;

    @FXML
    private ChoiceBox<Subject> subject;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea description;

    @FXML
    TextField mark;

    @FXML
    private Label classIn;

    private static Users currentStudent;

    private static ClassDB classDB = new ClassDB();

    public static Users getCurrentStudent() {
        return currentStudent;
    }

    public static void setCurrentStudent(Users currentStudent) {
        ModifyScoreController.currentStudent = currentStudent;
    }

    public static ClassDB getClassDB() {
        return classDB;
    }

    public static void setClassDB(ClassDB classDB) {
        ModifyScoreController.classDB = classDB;
    }

    public Grade grade;

    @FXML
    public void initialize() {
        ArrayList<ClassDB> classList = new ClassRepository().getClassListWithSupervisorId(Users.getActiveUser().getUserId());
        classChoice.getItems().addAll(classList);
        classChoice.setOnAction(this::setClass);
        if(ModifyScoreController.getClassDB().getName() != null){
            initializeSubjectList();
            classIn.setText(ModifyScoreController.getClassDB().toString());
            ArrayList<Users> sList = new UserRepository().getClassList(ModifyScoreController.getClassDB().getClass_id());
            studentList.getItems().addAll(sList);
            if(ModifyScoreController.getCurrentStudent()!=null){
                initializeScoreList();
            }
        }
        type.getItems().addAll(new TaskTypeRepository().getAllTaskTypes());

        studentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Users>() {
            @Override
            public void changed(ObservableValue<? extends Users> observable, Users oldValue, Users newValue) {
                ModifyScoreController.setCurrentStudent(studentList.getSelectionModel().getSelectedItem());
                reload();
            }
        });
        scoreList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Grade>() {
            @Override
            public void changed(ObservableValue<? extends Grade> observable, Grade oldValue, Grade newValue) {
                grade = scoreList.getSelectionModel().getSelectedItem();
                initializeInfo();
            }
        });

    }

    public void setClass(ActionEvent actionEvent) {
        ModifyScoreController.setClassDB(classChoice.getValue());
        reload();
    }

    public void initializeScoreList(){
        ArrayList<Integer> subjectIdList = new LessonPlanRepository().getSubjectIdByClassAndTeacher(
                ModifyScoreController.classDB.getClass_id(),
                Users.getActiveUser().getUserId());
        ArrayList<Subject> subjectList = new ArrayList<>();
        for(Integer id: subjectIdList){
            subjectList.add(new SubjectRepository().getSubjectById(id));
        }
        ArrayList<Grade> grades = new ArrayList<>();
        for(Subject subject: subjectList){
            ArrayList<Grade> subGrades = new GradeRepository().getGradesBySubjectandStudent(subject.getSubject_id(), ModifyScoreController.getCurrentStudent().getUserId());
            grades.addAll(subGrades);
        }
        scoreList.getItems().addAll(grades);
    }
    public void initializeSubjectList(){
        ArrayList<Integer> subjectIdList = new LessonPlanRepository().getSubjectIdByClassAndTeacher(
                ModifyScoreController.classDB.getClass_id(),
                Users.getActiveUser().getUserId());
        ArrayList<Subject> subjectList = new ArrayList<>();
        for(Integer id: subjectIdList){
            subjectList.add(new SubjectRepository().getSubjectById(id));
        }
        subject.getItems().addAll(subjectList);
    }

    public void initializeInfo(){
        TaskType task = new TaskTypeRepository().getTaskTypeById(grade.getTaskTypeId());
        Subject sub = new SubjectRepository().getSubjectById(grade.getSubjectId());
        mark.setText(""+grade.getGrade());
        type.setValue(task);
        subject.setValue(sub);
        date.setValue(grade.getDate().toLocalDate());
        description.setText(grade.getDescription());
    }

    @FXML
    public void goBack(ActionEvent event) {
        ModifyScoreController.getClassDB().setName(null);
        new Utilis().goToStarPage();
    }

    @FXML
    public void modify(){
        Grade newGrade = new Grade();
        newGrade.setGradeId(grade.getGradeId());
        newGrade.setSubject_id(subject.getValue().getSubject_id());
        newGrade.setTask_type_id(type.getValue().getTask_type_id());
        newGrade.setStudent_id(currentStudent.getUserId());
        newGrade.setGrade(Integer.parseInt(mark.getText()));
        newGrade.setDescription(description.getText());
        newGrade.setDate(Date.valueOf(date.getValue()));

        new GradeRepository().modifyGrade(newGrade);
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText("Zmodyfikowano ocenę!");
        conf.showAndWait();
        reload();
    }

    @FXML
    public void delete(){
        new GradeRepository().deleteGrade(grade.getGradeId());
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText("Usunięto ocenę!");
        conf.showAndWait();
        reload();
    }


    private void reload(){
        Main m = new Main();
        try{
            m.changeScene("modify_score.fxml",1280,720);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
