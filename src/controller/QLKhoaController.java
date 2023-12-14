package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.KhoaDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AlertMsg;
import model.Khoa;
import utils.TableUtil;

public class QLKhoaController implements Initializable {

    @FXML
    private TableColumn<Khoa, String> bm_maBM;

    @FXML
    private TableColumn<Khoa, String> bm_ngayNC;

    @FXML
    private TableColumn<Khoa, String> bm_sdt;

    @FXML
    private TableColumn<Khoa, String> bm_tenBM;

    @FXML
    private TableColumn<Khoa, String> bm_trgBM;

    @FXML
    private TextField f_bm_maBM;

    @FXML
    private TextField f_bm_ngayNC;

    @FXML
    private TextField f_bm_phong;

    @FXML
    private TextField f_bm_sdt;

    @FXML
    private TextField f_bm_tenBM;

    @FXML
    private TextField f_bm_trgBM;

    @FXML
    private TextField f_khoa_maKh;

    @FXML
    private TextField f_khoa_namTL;

    @FXML
    private TextField f_khoa_ngNC;

    @FXML
    private TextField f_khoa_phong;

    @FXML
    private TextField f_khoa_sdt;

    @FXML
    private TextField f_khoa_trgKh;

    @FXML
    private TextField f_khoa_tenKh;

    @FXML
    private TableColumn<Khoa, String> khoa_maKh;

    @FXML
    private TableColumn<Khoa, String> khoa_namTL;

    @FXML
    private TableColumn<Khoa, String> khoa_ngayNC;

    @FXML
    private TableColumn<Khoa, String> khoa_phong;

    @FXML
    private TableColumn<Khoa, String> khoa_sdt;

    @FXML
    private TableColumn<Khoa, String> khoa_tenKh;

    @FXML
    private TableColumn<Khoa, String> khoa_trgKhoa;

    @FXML
    private Label ma_label;

    @FXML
    private Label ma_label1;

    @FXML
    private Label ngaync_label;

    @FXML
    private Label ngaync_label1;

    @FXML
    private TableView<?> tableBM;

    @FXML
    private TableView<Khoa> tableKhoa;

    @FXML
    private Label ten_label;

    @FXML
    private Label ten_label1;

    @FXML
    private Label truongbm_label;

    @FXML
    private Label truongbm_label1;
    private AlertMsg alert;

    public void showAllKhoa()
    {
        try
        {
            ObservableList<Khoa> listKhoa = KhoaDAO.Instance().getAll();
            tableKhoa.setItems(listKhoa);
            khoa_maKh.setCellValueFactory(new PropertyValueFactory<>("maKhoa"));
            khoa_namTL.setCellValueFactory(new PropertyValueFactory<>("namTL"));
            khoa_phong.setCellValueFactory(new PropertyValueFactory<>("phong"));
            khoa_ngayNC.setCellValueFactory(new PropertyValueFactory<>("ngayNhanChuc"));
            khoa_tenKh.setCellValueFactory(new PropertyValueFactory<>("tenKhoa"));
            khoa_sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
            khoa_trgKhoa.setCellValueFactory(new PropertyValueFactory<>("truongKhoa"));
            TableUtil.autoResizeColumns(tableKhoa);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }   
    @FXML
    void clearAll(ActionEvent event) {

    }

    @FXML
    void deleteBM(ActionEvent event) {

    }

    @FXML
    void deleteKhoa(ActionEvent event) {

    }

    @FXML
    void insertBM(ActionEvent event) {

    }

    @FXML
    void insertKhoa(ActionEvent event) {
        Khoa khoa = null;
        try
        {
            if(f_khoa_maKh.getText().isEmpty()
            ||f_khoa_tenKh.getText().isEmpty()
            ||f_khoa_phong.getText().isEmpty()
            ||f_khoa_sdt.getText().isEmpty()
            ||f_khoa_namTL.getText().isEmpty()
            ||f_khoa_trgKh.getText().isEmpty()
            ||f_khoa_ngNC.getText().isEmpty())
            {
                alert = new AlertMsg();
                alert.showAndWait();
            }
            else if(KhoaDAO.Instance().isContain(f_khoa_maKh.getText()))
            {
                alert = new AlertMsg(AlertType.ERROR, "MaKhoa: " + f_khoa_maKh.getText() + " was already exist!");
                alert.showAndWait();
            }
            else
            {
                khoa = new Khoa();
                khoa.setMaKhoa(f_khoa_maKh.getText());
                khoa.setTenKhoa(f_khoa_tenKh.getText());
                khoa.setNamTL(f_khoa_namTL.getText());
                khoa.setPhong(f_khoa_phong.getText());
                khoa.setSdt(f_khoa_sdt.getText());
                khoa.setTruongKhoa(f_khoa_trgKh.getText());
                khoa.setNgayNhanChuc(f_khoa_ngNC.getText());

                KhoaDAO.Instance().insert(khoa);
                alert = new AlertMsg(AlertType.INFORMATION, "Successfully Added!!");
                alert.showAndWait();
                showAllKhoa();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void updateBM(ActionEvent event) {

    }

    @FXML
    void updateKhoa(ActionEvent event) {

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showAllKhoa();
    }
}

