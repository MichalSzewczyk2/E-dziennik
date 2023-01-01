package com.example.chatui;

import database.repository.ClassRepository;
import database.repository.LessonCalendarRepository;
import database.repository.TaskRepository;
import database.repository.TaskTypeRepository;
import database.tables.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventsControler {

    @FXML
    private TextField dateLabel;

    @FXML
    private TextArea descLabel;

    @FXML
    private ListView<Event> list;

    @FXML
    private TextField nameLabel;

    private Event ev;

    @FXML
    public void initialize(){
        list.getItems().addAll(getEvents());
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event events, Event t1){
                ev = list.getSelectionModel().getSelectedItem();
            }
        });
    }

    @FXML
    public void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    @FXML
    public void showEvent(MouseEvent event) {
        nameLabel.setText(ev.getName());
        dateLabel.setText(ev.getDate());
        descLabel.setText(ev.getDescription());
    }

    private ArrayList<Event> getEvents(){
        ArrayList<Event> s = new ArrayList<>();

        Users user = new Users().getActiveUser();
        ClassDB classDB = new ClassRepository().getClassByUser(user.getUserId());


        ArrayList<LessonCalendar> lc = new LessonCalendarRepository().getLessonCalendarsByClassId(classDB.getClass_id(), LocalDate.now());

        for (LessonCalendar l: lc) {
            Task task = new TaskRepository().getTaskByLessonId(l.getLesson_id());
            TaskType taskType = new TaskTypeRepository().getTaskTypeById(task.getTask_type_id());
            Event event = new Event();
            event.setDate(l.getDate().toString());
            event.setName(taskType.getName());
            event.setDescription(task.getDescription());
            event.setLesson_id(l.getLesson_id());
            event.setPlan_id(l.getPlan_id());
            event.setTask_type_id(taskType.getTask_type_id());
            s.add(event);
        }
        return s;
    }

}
