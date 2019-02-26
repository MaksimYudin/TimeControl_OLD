package org.russianfeature.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import com.utils.Config;
import org.russianfeature.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryWorkPlaceController {

    private Main mainApp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane dictionaryWorkPlace;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnTeachers;

    @FXML
    private Button btnLessons;

    @FXML
    private Button btnGroups;

    @FXML
    void btnStudentOnClick(ActionEvent event) {
        mainApp.removeWorkPlace("dictionaryStudentsMain");

        mainApp.showDictionaryStudentsMain();
    }

    @FXML
    void initialize() {

    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setPlaceProperty() {
        AnchorPane.setBottomAnchor(dictionaryWorkPlace, (double) Config.TOP_BOTTOM_PANE_HEIGHT);
        AnchorPane.setTopAnchor(dictionaryWorkPlace, (double) Config.TOP_BOTTOM_PANE_HEIGHT);
        AnchorPane.setLeftAnchor(dictionaryWorkPlace, (double) Config.BTN_MENU_WIDTH + 20);
        AnchorPane.setRightAnchor(dictionaryWorkPlace, 0.0);

        //bindLayoutsBlocks();

    }


}
