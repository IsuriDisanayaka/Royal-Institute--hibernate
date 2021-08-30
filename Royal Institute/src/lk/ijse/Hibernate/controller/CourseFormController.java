package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Hibernate.bo.BOFactory;
import lk.ijse.Hibernate.bo.BOType;
import lk.ijse.Hibernate.bo.custom.CourseBO;
import lk.ijse.Hibernate.dto.CourseDTO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.model.CourseTM;
import lk.ijse.Hibernate.model.StudentTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static sun.net.www.MimeTable.loadTable;


public class CourseFormController {
    public JFXTextField txtCode;
    public ComboBox cmbCourseType;
    public ComboBox cmbCourseName;
    public JFXTextField txtDuration;
    public Button btnSave;
    public Button btnUpdate;
    public ComboBox cmbDuration;
    public TableView<CourseTM> tblCourse;
    public TextField txtSearch;
    public Button btnDelete;

    public void initialize() {
        selectCourseName();
        selectCourseType();
        selectDuration();
        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CId"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("CourseType"));
        tblCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Duration"));
        loadTable();
    }

    private void selectCourseType() {
        ObservableList<String> observableList = FXCollections.observableArrayList("Academic qualifications", "Degree",
                "Professional qualification");
        cmbCourseType.setItems(observableList);
    }

    private void selectCourseName() {
        ObservableList<String> observableList = FXCollections.observableArrayList("English", "It", "Management", "Fashion Designing",
                "Law", "Medicine", "Web Designing");
        cmbCourseName.setItems(observableList);
    }

    private void selectDuration() {
        ObservableList<String> observableList = FXCollections.observableArrayList("One Year", "Three Month", "Six Month",
                "2years", "4years", "Three Years");
        cmbDuration.setItems(observableList);
    }

    public void SearchOnAction(ActionEvent actionEvent) throws Exception {
        CourseDTO courseDTO = courseBO.getCourse(txtSearch.getText());
        if (courseDTO != null) {
            txtCode.setText(courseDTO.getCId());
            cmbCourseName.setValue(courseDTO.getCourseName());
            cmbCourseType.setValue(courseDTO.getCourseType());
            cmbDuration.setValue(courseDTO.getDuration());

        }
    }

    CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    public void SaveOnAction(ActionEvent actionEvent) throws Exception {
        String CId = txtCode.getText();
        String CourseName = cmbCourseName.getValue() + "";
        String CourseType = cmbCourseType.getValue() + "";
        String Duration = cmbDuration.getValue() + "";

        CourseDTO courseDTO = new CourseDTO(
                CId,
                CourseName,
                CourseType,
                Duration
        );
        if (btnSave.getText().trim().equals("Save")) {
            save(courseDTO);
        } else {
            update(courseDTO);
        }
        loadTable();
        txtCode.clear();
        cmbCourseName.setValue(null);
        cmbCourseType.setValue(null);
        cmbDuration.setValue(null);
    }

    public void save(CourseDTO courseDTO) {
        try {
            boolean isAdded = courseBO.addCourse(courseDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added Sucess!").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Added Fail!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(CourseDTO courseDTO) {
        try {
            boolean isAdded = courseBO.updateCourse(courseDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "SucessFull").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable() {
        ObservableList<CourseTM> courses = tblCourse.getItems();
        courses.clear();
        try {
            List<CourseDTO> allCourses = courseBO.getAllCourses();
            for (CourseDTO course : allCourses) {
                courses.add(new CourseTM(course.getCId(),
                        course.getCourseName(),
                        course.getCourseType(),
                        course.getDuration()));
            }
            tblCourse.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = courseBO.updateCourse(new CourseDTO(
                txtCode.getText(),
                cmbCourseName.getValue() + "",
               cmbCourseType.getValue() + "",
                cmbDuration.getValue() + ""));
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
        loadTable();
        txtCode.clear();
        cmbCourseName.setValue(null);
        cmbCourseType.setValue(null);
        cmbDuration.setValue(null);

    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        String CId = txtCode.getText();
        String CourseName = cmbCourseName.getValue() + "";
        String CourseType = cmbCourseType.getValue() + "";
        String Duration = cmbDuration.getValue() + "";

        try {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to delete this..?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.YES)) {
                courseBO.deleteCourse(new CourseDTO(CId,
                        CourseName,
                        CourseType,
                        Duration));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTable();
        txtCode.clear();
        cmbCourseName.setValue(null);
        cmbCourseType.setValue(null);
        cmbDuration.setValue(null);

    }


}