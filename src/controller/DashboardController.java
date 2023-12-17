package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DashboardController {

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button congviec_btn;

    @FXML
    private Button debug_btn;

    @FXML
    private Button detai_btn;

    @FXML
    private Button giaovien_btn;

    @FXML
    private Button home_btn;

    @FXML
    private Button khoa_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    void handleDebug(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/DeBug.fxml"));
        borderpane.setCenter(view);
    }

    @FXML
    void handleShowCongViec(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLCongViec.fxml"));
        borderpane.setCenter(view);
    }

    @FXML
    void handleShowDeTai(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLDeTai.fxml"));
        borderpane.setCenter(view);
    }

    @FXML
    void handleShowHome(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        borderpane.setCenter(view);
    }

    @FXML
    void handleShowQLGiaoVIen(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLGiaoVien.fxml"));
        borderpane.setCenter(view);
    }

    @FXML
    void handleShowQLKhoa(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../view/QLKhoa.fxml"));
        borderpane.setCenter(view);
    }

}
