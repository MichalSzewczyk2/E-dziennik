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

public class AddTestController {

    @FXML
    private ChoiceBox<ClassDB> classChoice;

    @FXML
    private Label classIn;

    @FXML
    private DatePicker date;

    @FXML
    private ListView<LessonPlan> dayList;

    @FXML
    private TextArea description;

    @FXML
    private ChoiceBox<Subject> subject;

    @FXML
    private ChoiceBox<TaskType> type;

    @FXML
    private ChoiceBox<String> weekdayChoice;

    private LessonPlan currentPlan;
    private static String days[] = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek"};

    private static ClassDB classDB = new ClassDB();

    public static ClassDB getClassDB() {
        return classDB;
    }

    public static void setClassDB(ClassDB classDB) {
        AddTestController.classDB = classDB;
    }

    @FXML
    public void initialize() {
        ArrayList<ClassDB> classList = new ClassRepository().getClassListWithSupervisorId(Users.getActiveUser().getUserId());
        classChoice.getItems().addAll(classList);
        classChoice.setOnAction(this::setClass);
        if (AddTestController.getClassDB().getName() != null) {
            classIn.setText(AddTestController.getClassDB().toString());
            ArrayList<Users> sList = new UserRepository().getClassList(AddTestController.getClassDB().getClass_id());
        }
        weekdayChoice.getItems().addAll(days);
        weekdayChoice.setOnAction(this::setWeekday);
        type.getItems().addAll(new TaskTypeRepository().getAllTaskTypes());
        dayList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LessonPlan>() {
            @Override
            public void changed(ObservableValue<? extends LessonPlan> observable, LessonPlan oldValue, LessonPlan newValue) {
                currentPlan = dayList.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void setClass(ActionEvent actionEvent) {
        AddTestController.setClassDB(classChoice.getValue());
    }

    public void setWeekday(ActionEvent actionEvent) {
        ArrayList<LessonPlan> plans = new LessonPlanRepository().getLessonsByClassAndDay(classDB.getClass_id(), getDayFromNatural(weekdayChoice.getValue()));
        dayList.getItems().addAll(plans);
    }

    @FXML
    public void goBack() {
        AddTestController.getClassDB().setName(null);
        new Utilis().goToStarPage();
    }

    @FXML
    public void addTest() {
        int calId = new LessonCalendarRepository().addLessonCalendar(currentPlan.getPlan_id(), date.getValue());
        new TaskRepository().addTask(calId, type.getValue().getTask_type_id(), description.getText());
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setHeaderText("Dodano aktywność!");
        conf.showAndWait();
    }

    public String getDayFromNatural(String s) {
        switch (s) {
            case "Poniedziałek":
                return "poniedzialek";
            case "Wtorek":
                return "wtorek";
            case "Środa":
                return "sroda";
            case "Czwartek":
                return "czwartek";
            case "Piątek":
                return "piatek";
            default:
                return "";
        }
    }

    private void reload() {
        Main m = new Main();
        try {
            m.changeScene("add_test.fxml", 1280, 720);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
