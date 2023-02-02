package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBord1Controller {
    public AnchorPane dashBordPane;
    public AnchorPane overViewPane;
    public JFXButton dashBord;

    public void signOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashBordPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/phoneshop/view/LoginForm.fxml"))));
        stage.centerOnScreen();
    }
    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {

        setUi("/lk/ijse/phoneshop/view/OverView");
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/CustomerManage");
    }

    public void employeeAtten(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/EmployeeAttendance");
    }

    public void employeeMana(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/EmployeeManage");
    }

    public void stockOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/StockManage");
    }

    public void orderOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/PlaceOrder");
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/ItemManage");
    }

    public void reportOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/Report");
    }

    public void returnOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/ReturnDetail");
    }

    public void repairOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/RepairManage");
    }
    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        overViewPane.getChildren().clear();
        overViewPane.getChildren().add(load);
    }



}
