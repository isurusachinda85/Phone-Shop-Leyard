package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane loginPane;
    public Label lblError1;
    public Label lblError2;
    public JFXButton btnLoging;

    private Matcher userName;
    private Matcher password;

    public void initialize(){
        setPattern();
    }
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("Admin")&&txtPassword.getText().equals("1234")) {
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/phoneshop/view/DashBord1.fxml"))));
            stage.centerOnScreen();
            getSuccessNot();
            patternPerform();
        }else if(txtUserName.getText().equals("Isuru")&&txtPassword.getText().equals("2530")){
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/phoneshop/view/DashBord2.fxml"))));
            stage.centerOnScreen();
            getSuccessNot();
            patternPerform();
        }else if (txtUserName.getText().isEmpty()&&txtPassword.getText().isEmpty()){
            lblError1.setText("Enter username");
            lblError2.setText("Enter Password");
            getFailNot();
        }else {
            lblError2.setText("Incorrect username or password");
            getFailNot();
        }

    }
    public void getSuccessNot(){
        Notifications notification = Notifications.create().title("Success").text("Successfully login").graphic(null)
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notification.showConfirm();
    }
    public void getFailNot(){
        Notifications notification = Notifications.create().title("Fail").text("login Fail").graphic(null)
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notification.showError();
    }
    public void changeUserNameonAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();

    }
    public void setPattern(){
        Pattern userNamePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{5,}$");
        userName = userNamePattern.matcher(txtUserName.getText());

        Pattern passwordPattern = Pattern.compile("^[0-9]{4,}$");
        password = passwordPattern.matcher(txtPassword.getText());


    }
    public void patternPerform() {
        if (userName.matches()) {
            if (password.matches()) {

                    } else {
                        txtPassword.requestFocus();
                        txtPassword.setFocusColor(Paint.valueOf("Red"));
                        lblError2.setText("Invalid Password");
                    }
                } else {
                    txtUserName.requestFocus();
                    txtUserName.setFocusColor(Paint.valueOf("Red"));
                    lblError1.setText("Invalid User Name");
                }

    }
    public void signInAction(ActionEvent actionEvent) {
        btnLoging.fire();
    }

    public void userNameOnKeyRelese(KeyEvent keyEvent) {
            lblError1.setText("");
            txtUserName.setFocusColor(Paint.valueOf("Blue"));

            Pattern userNamePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{5,}$");
            userName = userNamePattern.matcher(txtUserName.getText());

            if (!userName.matches()){
                txtUserName.requestFocus();
                txtUserName.setFocusColor(Paint.valueOf("Red"));
                lblError1.setText("Invalid UserName");
            }
        }


    public void passwordOnKeyRelese(KeyEvent keyEvent) {
        lblError2.setText("");
        txtPassword.setFocusColor(Paint.valueOf("Blue"));

        Pattern passwordPattern = Pattern.compile("^[0-9]{4,}$");
        password = passwordPattern.matcher(txtPassword.getText());

        if (!password.matches()){
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Paint.valueOf("Red"));
            lblError2.setText("Invalid Password");
        }
    }
}
