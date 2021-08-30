package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.Hibernate.bo.BOFactory;
import lk.ijse.Hibernate.bo.BOType;
import lk.ijse.Hibernate.bo.custom.StudentBO;

import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.entity.Student;
import lk.ijse.Hibernate.model.StudentTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class StudentFormController {
    public ComboBox cmbGender;
    public DatePicker dteDate;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtId;
    public Button btnSave;
    public Button btnUpdate;
    public TableView<StudentTM>tblStudent;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colBirth;
    public TableColumn colGender;
    public TableColumn colAddress;
    public Button btnDelete;
    public TextField txtSerach;

    public void initialize() throws Exception {
        selectGender();
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Gender"));
        loadTable();
        tblStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
                btnDelete.setDisable(false);
                btnSave.setText("Update");

                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    return;
                }

               txtId.setText(selectedItem.getId());
                txtName.setText(selectedItem.getStudentName());
                txtAddress.setText(selectedItem.getAddress());
                txtContact.setText(selectedItem.getContact());
               dteDate.setValue(LocalDate.parse(selectedItem.getDate()));
                cmbGender.setValue(selectedItem.getGender());
    }
        });
    }


    StudentBO studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);


    public void SaveOnAction(ActionEvent actionEvent) throws Exception {
         String Id =txtId.getText();
        String StudentName= txtName.getText();
        String Address=txtAddress.getText();
        String Contact=  txtContact.getText();
       String Date=dteDate.getValue() + "";
       String Gender=cmbGender.getValue() + "";


       StudentDTO studentDTO=new StudentDTO(Id,StudentName,Address,
               Contact, Date ,Gender);

        if (btnSave.getText().trim().equals("Save")) {
            save(studentDTO);
            System.out.println("con"+studentDTO);
        } else {
            update(studentDTO);

        }
        loadTable();
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        dteDate.setValue(null);
        cmbGender.setValue(null);
    }
    public void save(StudentDTO studentDTO) {
        try {
            boolean isAdded = studentBO.addStudent(studentDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added Sucess!").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Added Fail!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void update(StudentDTO studentDTO) {
        try {
            boolean isAdded = studentBO.updateStudent(studentDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Sucess Full").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadTable() {
        ObservableList<StudentTM> students = tblStudent.getItems();
        students.clear();
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO student : allStudents) {
               students.add(new StudentTM(student.getId(),
                       student.getStudentName(),
                       student.getAddress(),
                       student.getContact(),
                       student.getDate(),
                       student.getGender()));
            }
           tblStudent.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SearchOnAction(ActionEvent actionEvent) throws Exception {
       StudentDTO studentDTO=studentBO.getStudent(txtSerach.getText());
        if (studentDTO!=null){
            txtId.setText(studentDTO.getId());
            txtName.setText(studentDTO.getStudentName());
            txtAddress.setText(studentDTO.getAddress());
            txtContact.setText(studentDTO.getContact());
            dteDate.setValue(LocalDate.parse(studentDTO.getDate()));
            cmbGender.setValue(studentDTO.getGender());

        }

    }


    public void selectGender() {
        ObservableList<String> observableList = FXCollections.observableArrayList("Female", "Male");
        cmbGender.setItems(observableList);
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = studentBO.updateStudent(new StudentDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                dteDate.getValue() + "",
                cmbGender.getValue() + ""));
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
        loadTable();
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        dteDate.setValue(null);
        cmbGender.setValue(null);

    }

    public void StudentFrom(MouseEvent mouseEvent) {
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        String Id =txtId.getText();
        String StudentName= txtName.getText();
        String Address=txtAddress.getText();
        String Contact=  txtContact.getText();
        String Date=dteDate.getValue() + "";
        String Gender=cmbGender.getValue() + "";

        try {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to delete this..?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.YES)) {
                studentBO.deleteStudent(new StudentDTO(Id,StudentName,Address,
                        Contact, Date ,Gender));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTable();
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        dteDate.setValue(null);
        cmbGender.setValue(null);



}
}
