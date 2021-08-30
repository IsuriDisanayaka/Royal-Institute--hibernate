package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Hibernate.bo.BOFactory;
import lk.ijse.Hibernate.bo.BOType;
import lk.ijse.Hibernate.bo.custom.CourseBO;
import lk.ijse.Hibernate.bo.custom.RegistrationBO;
import lk.ijse.Hibernate.bo.custom.StudentBO;
import lk.ijse.Hibernate.dto.CourseDTO;
import lk.ijse.Hibernate.dto.RegistrationDTO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.model.RegistrationTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class RegistrationFromController {
    public TextField txtSearch;
    public ComboBox cmbStudentID;
    public ComboBox cmbCourseId;
    public Button btnSave;
    public JFXDatePicker dteDate;
    public JFXTextField txtFee;
    public JFXTextField txtReg;
    public TableView<RegistrationTM>tblReg;
    public Button btnDelete;

    StudentBO studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);
    CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    public void initialize() throws Exception {
        getSID();
        getCID();

        tblReg.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("regNo"));
        tblReg.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("regDate"));
        tblReg.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("regFee"));
        tblReg.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("studentDTO"));
        tblReg.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("courseDTO"));
        loadTable();
        tblReg.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegistrationTM>() {
            @Override
           public void changed(ObservableValue<? extends RegistrationTM> observable, RegistrationTM oldValue, RegistrationTM newValue) {
               btnDelete.setDisable(false);
               btnSave.setText("Update");
               RegistrationTM selectedItem = tblReg.getSelectionModel().getSelectedItem();

              if (selectedItem == null) {
                   return;
              }

              txtReg.setText(String.valueOf((selectedItem.getRegNo())));
                dteDate.setValue(LocalDate.parse(selectedItem.getRegDate()));
                txtFee.setText(String.valueOf(selectedItem.getRegFee()));
                cmbStudentID.setValue(selectedItem.getStudentDTO());
                cmbCourseId.setValue(selectedItem.getCourseDTO());



            }
        });
    }

    public void SearchOnAction(ActionEvent actionEvent) {
            }

            RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOType.REGISTRATION);

            public void SaveOnAction(ActionEvent actionEvent) throws Exception {
                try {
                    int regId = Integer.parseInt(txtReg.getText());
                    String regDate = dteDate.getValue().toString();
                    double fee = Double.parseDouble(txtFee.getText());
                    StudentDTO studentDTO = new StudentDTO(cmbStudentID.getValue().toString());
                    CourseDTO courseDTO = new CourseDTO(cmbCourseId.getValue().toString());

                    boolean flag = registrationBO.addRegistration(new RegistrationDTO(regId, regDate, fee, studentDTO, courseDTO));
                    if (flag) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Student Registerd!", ButtonType.OK).show();
                    }
                } finally {

                }
            }

            public void getSID() {
                try {
                    List<StudentDTO> list = studentBO.getAllStudents();
                    for (StudentDTO s : list) {
                        cmbStudentID.getItems().addAll((s.getId()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void getCID() {
                try {
                    List<CourseDTO> list = courseBO.getAllCourses();
                    for (CourseDTO c : list) {
                        cmbCourseId.getItems().addAll((c.getCId()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    public void loadTable() {
        ObservableList<RegistrationTM> registrations =tblReg.getItems();
        registrations.clear();
        try {
            List<RegistrationDTO> allRegistrations = registrationBO.getAllRegistrations();
            for (RegistrationDTO registration : allRegistrations) {
              registrations.add(new RegistrationTM(registration.getRegNo(),
                      registration.getRegDate(),
                      registration.getRegFee(),
                              registration.getStudentDTO(),
                              registration.getCourseDTO()));
            }
            tblReg.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void DeleteOnAction(ActionEvent actionEvent) {
        int regId = Integer.parseInt(txtReg.getText());
        String regDate = dteDate.getValue().toString();
        double fee = Double.parseDouble(txtFee.getText());
        StudentDTO studentDTO = new StudentDTO(cmbStudentID.getValue().toString());
        CourseDTO courseDTO = new CourseDTO(cmbCourseId.getValue().toString());
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to delete this..?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.YES)) {
                registrationBO.deleteRegistration(new RegistrationDTO(regId, regDate, fee, studentDTO, courseDTO));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTable();
        txtReg.clear();
        dteDate.setValue(null);
        txtFee.clear();
        cmbStudentID.setValue(null);
        cmbCourseId.setValue(null);
    }
    }