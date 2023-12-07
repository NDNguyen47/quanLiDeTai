package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Updated
public class GiaoVien
{
    private StringProperty maGV;
    private StringProperty hoTen;
    private StringProperty gioiTinh;
    private StringProperty diaChi;
    private DoubleProperty luong;
    private StringProperty maBM;

    public GiaoVien()
    {
        this.maGV = new SimpleStringProperty();
        this.hoTen = new SimpleStringProperty();
        this.gioiTinh = new SimpleStringProperty();
        this.diaChi = new SimpleStringProperty();
        this.luong = new SimpleDoubleProperty();
        this.maBM = new SimpleStringProperty();
    }
    // maGV
    public String getMaGV()
    {
        return maGV.get();
    }
    public void setMaGV(String maGV)
    {
        this.maGV.set(maGV);
    }
    public StringProperty maGVProperty()
    {
        return maGV;
    }
    // hoTen
    public String getHoTen()
    {
        return hoTen.get();
    }
    public void setHoTen(String hoTen)
    {
        this.hoTen.set(hoTen);
    }
    public StringProperty hoTenProperty()
    {
        return hoTen;
    }
    // gioiTinh
    public String getGioiTinh()
    {
        return gioiTinh.get();
    }
    public void setGioiTinh(String gioiTinh)
    {
        this.gioiTinh.set(gioiTinh);
    }
    public StringProperty gioiTinhProperty()
    {
        return gioiTinh;
    }
    // diachi
    public String getDiaChi()
    {
        return diaChi.get();
    }
    public void setDiaChi(String diaChi)
    {
        this.diaChi.set(diaChi);
    }
    public StringProperty diaChiProperty()
    {
        return diaChi;
    }
    // luong
    public double getLuong()
    {
        return this.luong.get();
    }
    public void setLuong(double luong)
    {
        this.luong.set(luong);
    }
    public DoubleProperty luongProperty()
    {
        return luong;
    }
    // maBM
    public String getMaBM()
    {
        return maBM.get();
    }
    public void setMaBM(String maBM)
    {
        this.maBM.set(maBM);
    }
    public StringProperty maBMProperty()
    {
        return maBM;
    }
}
