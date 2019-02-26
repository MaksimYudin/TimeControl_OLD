package org.russianfeature.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.hibernate.crud.operations.StudentService;
import com.utils.EnumYesNo;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.utils.Config;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.russianfeature.Main;
import org.russianfeature.model.Student;
import com.utils.EnumAction;

public class DictionaryStudentsMain {

    private Main mainApp;
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private Student currentStudent;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane dictionaryStudentsMain;

    @FXML
    private TableView<Student> tableStudents;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> firstName;

    @FXML
    private TableColumn<Student, String> secondName;

    @FXML
    private TableColumn<Student, String> lastName;

    @FXML
    private TableColumn<Student, String> createDate;

    @FXML
    private TableColumn<Student, String> birthDate;

    @FXML
    private TableColumn<Student, String> comment;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    void initialize() {
        showStudents();

        SetColumnProperty();

        setBtnImages();
    }

    @FXML
    void btnAddOnClick(ActionEvent event) {
        showEditCreateWindow(EnumAction.CREATE);
        loadDataInTableView();
    }

    @FXML
    void btnDeleteOnClick(ActionEvent event) {

        showYesNoQuestionWindow();
        if (QuestionYeaNoController.answer == EnumYesNo.NO)
            return;;

        Student student = tableStudents.getSelectionModel().getSelectedItem();
        if (student == null)
            return;
        StudentService studentService = new StudentService();
        studentService.delete(student);

        loadDataInTableView();
    }

    @FXML
    void btnEditOnClick(ActionEvent event) {
        showEditCreateWindow(EnumAction.UPDATE);
    }

    void showEditCreateWindow(EnumAction action) {

        Student student = tableStudents.getSelectionModel().getSelectedItem();
        if (student == null
                && action == EnumAction.UPDATE)
            return;

        try {

            Stage studentDialogAction = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/StudentForm.fxml"));

            AnchorPane studentAction = loader.load();

            studentAction.setId("dictionaryStudentsFormAction");

            // Bind controller and main app
            StudentController controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.setStage(studentDialogAction);
            controller.setAction(action);
            if (action == EnumAction.UPDATE) {
                controller.setStudent(student);
                controller.setFieldValues();
            }

            studentDialogAction.setScene(new Scene(studentAction));
            studentDialogAction.initOwner(mainApp.getPrimaryStage());
            studentDialogAction.initModality(Modality.APPLICATION_MODAL);
            studentDialogAction.showAndWait();

            currentStudent = controller.getStudent();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showYesNoQuestionWindow() {

        try {

            Stage yesNoDialog = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/QuestionYesNo.fxml"));

            AnchorPane studentAction = loader.load();

            studentAction.setId("QuestionYesNo");

            // Bind controller and main app
            QuestionYeaNoController controller = loader.getController();
            controller.setStage(yesNoDialog);
            controller.setMainApp(mainApp);

            yesNoDialog.setScene(new Scene(studentAction));
            yesNoDialog.initOwner(mainApp.getPrimaryStage());
            yesNoDialog.initModality(Modality.APPLICATION_MODAL);
            yesNoDialog.showAndWait();

            //tableStudents.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setPlaceProperty() {
        AnchorPane.setBottomAnchor(dictionaryStudentsMain, (double) Config.TOP_BOTTOM_PANE_HEIGHT);
        AnchorPane.setTopAnchor(dictionaryStudentsMain, (double) Config.TOP_BOTTOM_PANE_HEIGHT);
        AnchorPane.setLeftAnchor(dictionaryStudentsMain, (double) Config.BTN_MENU_WIDTH + 20);
        AnchorPane.setRightAnchor(dictionaryStudentsMain, 0.0);

        //bindLayoutsBlocks();

    }

    void showStudents() {

        //Binding model and view elements
        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<Student, String>("secondName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        createDate.setCellValueFactory(new PropertyValueFactory<Student, String>("createDate"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Student, String>("birthDate"));
        comment.setCellValueFactory(new PropertyValueFactory<Student, String>("comment"));

        loadDataInTableView();

    }

    void SetColumnProperty() {
        firstName.prefWidthProperty().bind(dictionaryStudentsMain.widthProperty().divide(5.0));
        secondName.prefWidthProperty().bind(dictionaryStudentsMain.widthProperty().divide(5.0));
        lastName.prefWidthProperty().bind(dictionaryStudentsMain.widthProperty().divide(5.0));
        createDate.setPrefWidth(80);
        birthDate.setPrefWidth(80);
        id.setVisible(false);

        // set column createDate date format
        createDate.setCellFactory(column -> {
            TableCell<Student, String> cell = new TableCell<>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(java.sql.Date.valueOf(item)));
                    }
                }
            };

            return cell;
        });

        // set column birthDate date format
        birthDate.setCellFactory(column -> {
            TableCell<Student, String> cell = new TableCell<>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(java.sql.Date.valueOf(item)));
                    }
                }
            };

            return cell;
        });
    }

    void setBtnImages() {

        Image image = null;
        ImageView ImgView = null;
        try {
            image = new Image(getClass().getClassLoader().getResourceAsStream("images/add.png"));
            ImgView = new ImageView(image);
            ImgView.setFitHeight(30);
            ImgView.setFitWidth(30);
            btnAdd.setGraphic(ImgView);

            image = new Image(getClass().getClassLoader().getResourceAsStream("images/edit.png"));
            ImgView = new ImageView(image);
            ImgView.setFitHeight(30);
            ImgView.setFitWidth(30);
            btnEdit.setGraphic(ImgView);

            image = new Image(getClass().getClassLoader().getResourceAsStream("images/remove.png"));
            ImgView = new ImageView(image);
            ImgView.setFitHeight(30);
            ImgView.setFitWidth(30);
            btnDelete.setGraphic(ImgView);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    private ObservableList<Student> getStudentList() {

        StudentService studentService = new StudentService();
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        List<Student> stList = studentService.getAllData();

        for (Student student : stList) {
            studentList.add(student);
        }

        return studentList;
    }

    private void loadDataInTableView() {
        studentList = getStudentList();
        tableStudents.setItems(getStudentList());
        if (currentStudent == null
                && studentList.size() > 0) {
            tableStudents.getSelectionModel().select(0);
            tableStudents.getFocusModel().focus(0);
        } else {
            Integer index = getIndexByStudent(currentStudent);
            if (index >= 0) {
                tableStudents.requestFocus();
                tableStudents.getSelectionModel().select(index);
                tableStudents.getFocusModel().focus(index);
            }

        }
    }

    private Integer getIndexByStudent(Student student) {
        Integer index = 0;
        Integer studentId = student.getId();
        for (Student st : studentList) {
            if (st.getId() == studentId) {
                return index;
            }
            index++;
        }
        return -1;
    }
}