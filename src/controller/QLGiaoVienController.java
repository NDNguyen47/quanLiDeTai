package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;

import dao.GiaoVienDAO;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GiaoVien;
import utils.TableUtil;

public class QLGiaoVienController implements Initializable {
    @FXML
    private TableColumn<GiaoVien, String> boMon;

    @FXML
    private TableColumn<GiaoVien, String> diaChi;

    @FXML
    private TableColumn<GiaoVien, String> gioiTinh;

    @FXML
    private TableColumn<GiaoVien, String> luong;

    @FXML
    private TableColumn<GiaoVien, String> maGV;

    @FXML
    private TableColumn<GiaoVien, String> ngaySinh;

    @FXML
    private TableColumn<GiaoVien, String> hoTen;

    @FXML
    private TableColumn<String, String> sdt;

    @FXML
    private TableView<GiaoVien> tableGV;

    @FXML
    private TableView<String> sdtTable;

    public void showAllData()
    {
        ObservableList<GiaoVien> listGV = GiaoVienDAO.Instance().getAll();
        tableGV.setItems(listGV);
        maGV.setCellValueFactory(new PropertyValueFactory<>("maGV"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        boMon.setCellValueFactory(new PropertyValueFactory<>("maBM"));
        luong.setCellValueFactory(new PropertyValueFactory<>("luong"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showAllData();
        TableUtil.autoResizeColumns(tableGV);
    }
}
