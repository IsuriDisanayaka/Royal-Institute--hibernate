package lk.ijse.Hibernate.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Hibernate.bo.BOFactory;
import lk.ijse.Hibernate.bo.BOType;
import lk.ijse.Hibernate.bo.custom.CourseBO;
import lk.ijse.Hibernate.bo.custom.StudentBO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelectionFormController {


    public AnchorPane mainRoot;
    public AnchorPane border;
    public Label lblTime;
    public Label lblDate;


    public void initialize() throws IOException {
        lblDate.setText((String.valueOf(LocalDate.now())));
        setLblTime();
    }
    private void setLblTime() {
        Timeline timeline =new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter =DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public void initUi(String location){
        try {
            this.mainRoot.getChildren().clear();
            this.mainRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/"+location)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StudentFrom(MouseEvent mouseEvent) {

        initUi("StudentForm.fxml");
    }

    public void CourseFrom(MouseEvent mouseEvent) {
        initUi("CourseForm.fxml");
    }



        public void CourseFromOnAction(ActionEvent actionEvent) {
            initUi("CourseForm.fxml");


    }

    public void StudentFromOnAction(ActionEvent actionEvent) {

        initUi("StudentForm.fxml");
    }




    public void RegistationFrom(MouseEvent mouseEvent) {
        initUi("RegistrationFrom.fxml");
    }

    public void RegistationForm(ActionEvent actionEvent) {
        initUi("RegistrationFrom.fxml");
    }

    public void changepassword(ActionEvent actionEvent) {
    }
}
