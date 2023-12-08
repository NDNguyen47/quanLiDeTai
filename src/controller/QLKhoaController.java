package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class QLKhoaController {

    @FXML
    private Button choose_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label ma_label;

    @FXML
    private Label ngaync_label;

    @FXML
    private Label ten_label;

    @FXML
    private Label truongbm_label;

    private boolean isDefaultState = true;

    @FXML
    private void handleChooseButton() {
        if (isDefaultState) {
            ma_label.setText("Mã bộ môn");
            ten_label.setText("Tên bộ môn");
            truongbm_label.setText("Trưởng bộ môn");

            isDefaultState = false;
        } else {
            ma_label.setText("Mã khoa");
            ten_label.setText("Tên khoa");
            truongbm_label.setText("Trưởng khoa");

            isDefaultState = true;
        }
    }
}
