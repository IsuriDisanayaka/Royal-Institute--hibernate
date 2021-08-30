package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
   
    public AnchorPane anchpnewelcome;
    public AnchorPane layer1;
    public AnchorPane layer2;
    public Label lblwelcome;
    public Button btnSign;
    public Button btnlogin;
    public ImageView imguser;
    public ImageView imgLocak;
    public AnchorPane anchLogin;
    public Label lblid1;
    public Label lblid;
    public ImageView imgroyal;
    public ImageView imgroyal1;
    public RadioButton btnPw;
    public JFXTextField txtUserName;
   
    public JFXTextField txtpwtemp;
    public JFXPasswordField txtPassword;


    public void initialize(){
txtPassword.setVisible(false);
txtUserName.setVisible(false);
btnlogin.setVisible(false);
anchLogin.setVisible(false);
imgLocak.setVisible(false);
imguser.setVisible(false);
lblid1.setVisible(true);
imgroyal.setVisible(true);
imgroyal1.setVisible(false);


    }

    public void btnsignIn(MouseEvent mouseEvent) {
        TranslateTransition alide =new TranslateTransition();
        alide.setDuration(Duration.seconds(0.7));
        alide.setNode(layer2);
         alide.setToX(600);
         alide.play();

         layer1.setTranslateX(-350);
         btnSign.setVisible(false);
         lblwelcome.setVisible(true);
         btnlogin.setVisible(true);
        txtPassword.setVisible(true);
        txtUserName.setVisible(true);
        btnlogin.setVisible(true);
        anchLogin.setVisible(true);
        imgLocak.setVisible(true);
        imguser.setVisible(true);
        lblid1.setVisible(true);
        lblid.setVisible(true);
        imgroyal.setVisible(false);
        imgroyal1.setVisible(true);

    }


    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (password.length() > 0 &&username.length() > 0) {
            System.out.println(password);
            System.out.println(username+"aaa");
            if (true ) {

                Stage stage = (Stage)anchpnewelcome.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/SelectionForm.fxml"))));

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again!!", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty!!", ButtonType.OK).show();
        }


    }




    public void ShowPassword(ActionEvent actionEvent) {
        if (btnPw.isSelected()){
            String  password = txtPassword.getText();
            txtpwtemp.setText(password);
            txtpwtemp.setVisible(true);
            txtPassword.setVisible(false);


        }else{
            txtpwtemp.setVisible(false);
            txtPassword.setText(txtpwtemp.getText());
            txtPassword.setVisible(true);
        }

    }
    public void ExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
