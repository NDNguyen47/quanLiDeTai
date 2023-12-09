package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GiaoVien;
import model.SDT;
import utils.DBUtil;

public class GiaoVienDAO implements DAO<GiaoVien>
{
    private static GiaoVienDAO instance;
    private GiaoVienDAO()
    {
    }
    public static GiaoVienDAO Instance()
    {
        if(instance == null)
        {
            instance = new GiaoVienDAO();
        }
        return instance;
    }
    // Method lấy danh sách toàn bộ giáo viên
    @Override public ObservableList<GiaoVien> getAll()
    {
        String query = "SELECT * FROM giaovien";
        ObservableList<GiaoVien> giaoVienList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            while(resultSet.next())
            {
                giaoVienList.add(getFromResultSet(resultSet));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return giaoVienList;
    }

    private GiaoVien getFromResultSet(ResultSet resultSet) throws SQLException
    {
        GiaoVien gv = new GiaoVien();
        gv.setMaGV(resultSet.getString("MAGV"));
        gv.setHoTen(resultSet.getString("HOTEN"));
        gv.setGioiTinh(resultSet.getString("GT"));
        gv.setNgaySinh(resultSet.getString("NGSINH"));
        gv.setDiaChi(resultSet.getString("DIACHI"));
        gv.setLuong(resultSet.getDouble("LUONG"));
        gv.setMaBM(resultSet.getString("MABM"));
        return gv;
    }
    // Method lấy giáo viên theo MAGV
    @Override public GiaoVien get(String maGV)
    {
        String query = "SELECT * FROM giaovien WHERE MAGV='" + maGV + "'";
        GiaoVien gv = null;
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            if(resultSet != null)
            {
                resultSet.next();
                gv = getFromResultSet(resultSet);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return gv;
    }
    // Method kiểm tra giáo viên có tồn tại trong bảng không
    public boolean containID(String maGV) throws SQLException
    {
        String query = "SELECT * FROM giaovien WHERE MAGV='" + maGV + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
    // Method insert giáo viên vào CSDL
    @Override public void insert(GiaoVien gv)
    {
        String query = "INSERT INTO giaovien(MAGV, HOTEN, GT, DIACHI, LUONG, MABM) VALUES"
                       + "('" + gv.getMaGV() + "','" + gv.getHoTen() + "','" + gv.getGioiTinh() + "','" + gv.getDiaChi()
                       + "'," + gv.getLuong() + ",'" + gv.getMaBM() + "')";
        
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method update thông tin giáo viên
    @Override public void update(GiaoVien gv)
    {
        String query = "UPDATE giaovien SET "
                       + "HOTEN='" + gv.getHoTen() + "',"
                       + "GT='" + gv.getGioiTinh() + "',"
                       + "DIACHI='" + gv.getDiaChi() + "',"
                       + "LUONG=" + gv.getLuong() + ","
                       + "MABM='" + gv.getMaBM() + "'"
                       + "WHERE MAGV='" + gv.getMaGV() + "'";

        
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method xóa giáo viên khỏi CSDL
    @Override public void delete(String maGV)
    {
        String query = "DELETE FROM giaovien WHERE MAGV='" + maGV + "'";
        
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method lấy danh sách SDT theo MAGV
    public ObservableList<SDT> getSDTList(String maGV)
    {
        String query = "SELECT SDT FROM gv_dt WHERE MAGV = '" + maGV + "'";
        ObservableList<SDT> sdtList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            while(resultSet.next())
            {
                SDT sdt =  new SDT();
                sdt.setSdt(resultSet.getString(1));
                sdtList.add(sdt);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sdtList;
    }
    // Method insert SDT mới
    public void insertSDT(String maGV, String sdt)
    {
        String query = "INSERT INTO gv_dt(MAGV, SDT) VALUES"
                       + "('" + maGV + "','" + sdt + "')";
        
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method xóa SDT
    public void deleteSDT(String maGV, String sdt)
    {
        String query = "DELETE FROM gv_dt WHERE MAGV='" + maGV + "' AND SDT='" + sdt + "'";
        
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
