package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BoMon;
import utils.DBUtil;

public class BoMonDAO implements DAO<BoMon>
{
    private static BoMonDAO instance;
    private BoMonDAO()
    {
    }
    public static BoMonDAO Instance()
    {
        if(instance == null)
        {
            instance = new BoMonDAO();
        }
        return instance;
    }
    // Method lấy danh sách toàn bộ boMon
    @Override public ObservableList<BoMon> getAll()
    {
        String query = "SELECT * FROM bomon";
        ObservableList<BoMon> boMonList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            while(resultSet.next())
            {
                boMonList.add(getFromResultSet(resultSet));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return boMonList;
    }

    private BoMon getFromResultSet(ResultSet resultSet) throws SQLException
    {
        BoMon bomon = new BoMon();
        bomon.setMaKhoa(resultSet.getString("MAKHOA"));
        bomon.setMaBM(resultSet.getString("MABM"));
        bomon.setTenBM(resultSet.getString("TENBM"));
        bomon.setPhong(resultSet.getString("PHONG"));
        bomon.setSdt(resultSet.getString("SDT"));
        bomon.setTruongBM(resultSet.getString("TRGBM"));
        bomon.setNgayNhanChuc(resultSet.getString("NGAYNC"));
        return bomon;
    }
    // Method lấy bomon theo MABM
    @Override public BoMon get(String maBM)
    {
        String query = "SELECT * FROM bomon WHERE MABM='" + maBM + "'";
        BoMon bomon = null;
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            if(resultSet != null)
            {
                resultSet.next();
                bomon = getFromResultSet(resultSet);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return bomon;
    }
    // Method kiểm tra bomon có tồn tại trong bảng không
    public boolean isContain(String maBM) throws SQLException
    {
        String query = "SELECT * FROM bomon WHERE MABM='" + maBM + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
    // Method insert bomon vào CSDL
    @Override public void insert(BoMon bomon)
    {
        String query = "INSERT INTO bomon(MABM, TENBM, MAKHOA, PHONG, SDT, TRGBM, NGAYNC) VALUES"
                       + "('" + bomon.getMaBM() + "','" + bomon.getTenBM() + "','" + bomon.getMaKhoa() + "','"
                       + bomon.getPhong() + "','" + bomon.getSdt() + "'," + bomon.getTruongBM() + ",'"
                       + bomon.getNgayNhanChuc() + "')";

        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method update thông tin bomon
    @Override public void update(BoMon bomon)
    {
        String query = "UPDATE bomon SET "
                       + "TENBM='" + bomon.getTenBM() + "',"
                       + "MAKHOA='" + bomon.getMaKhoa() + "',"
                       + "PHONG='" + bomon.getPhong() + "',"
                       + "SDT='" + bomon.getSdt() + "',"
                       + "TRGBM='" + bomon.getTruongBM() + "',"
                       + "NGAYNC='" + bomon.getNgayNhanChuc() + "'"
                       + "WHERE MABM='" + bomon.getMaKhoa() + "'";


        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method xóa bomon khỏi CSDL
    @Override public void delete(String maBM)
    {
        String query = "DELETE FROM bomon WHERE MABM='" + maBM + "'";

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
