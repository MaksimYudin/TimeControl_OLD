package org.russianfeature.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.hibernate.crud.operations.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.russianfeature.Main;
import com.utils.EnumAction;
import org.russianfeature.model.Student;
import com.utils.CommonUtil;

public class StudentController {

    private Main mainApp;
    private Stage stage;
    private Student student;
    private EnumAction action;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fldFirstName;

    @FXML
    private TextField fldSecondName;

    @FXML
    private TextField fldLastName;

    @FXML
    private TextField fldBirthDate;

    @FXML
    private TextArea taComment;

    @FXML
    void btnCancelOnClick(ActionEvent event) {
        stage.close();
    }

    @FXML
    void btnOKOnClick(ActionEvent event) {
        if (action == EnumAction.UPDATE)
            updateStudent();
        else
            createStudent();

        stage.close();
    }

    @FXML
    void initialize() {

    }

    @FXML
    void firstNameOnEnter(ActionEvent event) {
        TextField textField = (TextField) event.getSource();
        setTextFileldCorrectValue(event, textField);
    }
    @FXML
    void lastNameOnEnter(ActionEvent event) {
        TextField textField = (TextField) event.getSource();
        setTextFileldCorrectValue(event, textField);
    }

    @FXML
    void secondNameOnEnter(ActionEvent event) {
        TextField textField = (TextField) event.getSource();
        setTextFileldCorrectValue(event, textField);
    }

    void updateStudent() {
        StudentService studService = new StudentService();
        setStudentProperty(student);
        studService.update(student);
    }

    public Student getStudent() {
        return student;
    }

    void createStudent() {
        StudentService studService = new StudentService();
        Student newStudent = new Student();
        setStudentProperty(newStudent);
        studService.persist(newStudent);
        student = newStudent;
    }

    void setStudentProperty(Student entity) {
        entity.setFirstName(fldFirstName.getText());
        entity.setSecondName(fldSecondName.getText());
        entity.setLastName(fldLastName.getText());
        entity.setComment(taComment.getText());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (action == EnumAction.CREATE) {
            Date dt = new Date();
            String dateToDB = format.format(dt);
            entity.setCreateDate(dateToDB);
        }

        if (fldBirthDate.getText() != null) {
            try {
                DateFormat sourceFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = sourceFormat.parse(fldBirthDate.getText());
                entity.setBirthDate(format.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    void setTextFileldCorrectValue(ActionEvent event, TextField textField) {
        TextField firstName = (TextField) event.getSource();
        String text = firstName.getText();
        firstName.setText(CommonUtil.convertFirstLetterToUpperCase(text));
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setAction(EnumAction action) {
        this.action = action;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stg) {
        stage = stg;
    }

    public void setFieldValues() {
        fldFirstName.setText(student.getFirstName());
        fldSecondName.setText(student.getSecondName());
        fldLastName.setText(student.getLastName());
        taComment.setText(student.getComment());

        if (student.getBirthDate() != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                fldBirthDate.setText(format.format(java.sql.Date.valueOf(student.getBirthDate())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

