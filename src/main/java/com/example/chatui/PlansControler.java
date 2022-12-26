package com.example.chatui;

import database.repository.*;
import database.tables.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;


public class PlansControler {

    @FXML
    private ListView<String> list1;

    @FXML
    private ListView<String> list2;

    @FXML
    private ListView<String> list3;

    @FXML
    private ListView<String> list4;

    @FXML
    private ListView<String> list5;

    @FXML
    public void initialize() {

        list1.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(list1.getPrefWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
        list2.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(list2.getPrefWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
        list3.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(list3.getPrefWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
        list4.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(list4.getPrefWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
        list5.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(list5.getPrefWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });

        list1.getItems().addAll(fillTable("poniedzialek"));
        list2.getItems().addAll(fillTable("wtorek"));
        list3.getItems().addAll(fillTable("sroda"));
        list4.getItems().addAll(fillTable("czwartek"));
        list5.getItems().addAll(fillTable("piatek"));
    }

    @FXML
    public void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    public ArrayList<String> fillTable(String day) {

        ArrayList<String> list = new ArrayList<>();

        Users user = new Users().getActiveUser();
        ClassDB classDB = new ClassRepository().getClassByUser(user.getUser_id());

        ArrayList<LessonPlan> plan = new LessonPlanRepository().getLessonsByClassAndDay(classDB.getClass_id(), day);

        for (LessonPlan p : plan) {

            Subject subject = new SubjectRepository().getSubjectById(p.getSubject_id());
            Classroom classroom = new ClassroomRepository().getClassroomById(p.getClassroom_id());
            Users teacher = new UserRepository().getUserById(p.getTeacher_id());

            String s = trimLastThreeCharacters(p.getStart().toString()) + " - "
                    + trimLastThreeCharacters(p.getEnd().toString()) +
                    "\n" + subject.getName() +
                    "\nnauczyciel: " + teacher.getName() + " " + teacher.getSurname() +
                    "\nklasa: " + classroom.getNumber() + "    piętro: " + classroom.getFloor();
            list.add(s);
        }


        return list;
    }

    public static String trimLastThreeCharacters(String input) {
        if (input.length() > 3) {
            return input.substring(0, input.length() - 3);
        } else {
            return input;
        }
    }

}
