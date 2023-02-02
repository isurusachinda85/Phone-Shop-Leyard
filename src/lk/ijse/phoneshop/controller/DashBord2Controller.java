package lk.ijse.phoneshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBord2Controller {

    @FXML
    private AnchorPane dashBordPane;

    @FXML
    private AnchorPane overViewPane;

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/phoneshop/view/CustomerManage");
    }

    @FXML
    void importDetailOnAction(ActionEvent event) {

    }

    @FXML
    void itemOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/phoneshop/view/ItemManage");
    }

    @FXML
    void orderOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/phoneshop/view/PlaceOrder");
    }

    @FXML
    void overViewOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/phoneshop/view/OverView");
    }

    @FXML
    void reportOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/phoneshop/view/Report");
    }

    @FXML
    void returnOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/phoneshop/view/ReturnDetail");
    }

    @FXML
    void signOutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashBordPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/phoneshop/view/LoginForm.fxml"))));
        stage.centerOnScreen();
    }

    @FXML
    void stockOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/phoneshop/view/StockManage");
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
