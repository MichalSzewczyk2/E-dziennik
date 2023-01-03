package com.example.chatui.admin;

import com.example.chatui.Utilis;
import database.repository.ParentChildrenRepository;
import database.repository.UserRepository;
import database.tables.Users;
import database.tables.position_user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddParentChild {

    @FXML
    private BorderPane addPane;

    @FXML
    private Button back;

    @FXML
    private Label badChild;

    @FXML
    private Label badParent;

    @FXML
    private Label goodChild;

    @FXML
    private Label goodParent;

    @FXML
    private TextField nameChild;

    @FXML
    private TextField nameParent;

    @FXML
    private TextField surnameChild;

    @FXML
    private TextField surnameParent;

    private Users child;
    private Users parent;

    @FXML
    void addPC(ActionEvent event) {
        if (child != null && parent != null) {
            new ParentChildrenRepository().addRow(parent.getUserId(), child.getUserId());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Dodano relację rodzic-dziecko!");
        alert.showAndWait();
    }

    @FXML
    void goBack(ActionEvent event) {
        new Utilis().goToStarPage();
    }

    @FXML
    void searchChild(ActionEvent event) {
        child = new UserRepository().getUserByNameAndSurname(nameChild.getText(), surnameChild.getText());
        if (child != null && child.getPosition() == position_user.UCZEN) {
            goodChild.setOpacity(1);
            badChild.setOpacity(0);
        } else {
            goodChild.setOpacity(0);
            badChild.setOpacity(1);
            child = null;
            nameChild.setText("");
            surnameChild.setText("");
        }
    }

    @FXML
    void searchParent(ActionEvent event) {
        parent = new UserRepository().getUserByNameAndSurname(nameParent.getText(), surnameParent.getText());
        if (parent != null && parent.getPosition() == position_user.RODZIC) {
            goodParent.setOpacity(1);
            badParent.setOpacity(0);
        } else {
            goodParent.setOpacity(0);
            badParent.setOpacity(1);
            parent = null;
            nameParent.setText("");
            surnameParent.setText("");
        }
    }

}
