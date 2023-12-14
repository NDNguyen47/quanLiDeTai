package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Khoa;
import utils.DBUtil;


public class KhoaDAO implements DAO<Khoa>
{
    private static KhoaDAO instance;
    private KhoaDAO()
    {
    }
    public static KhoaDAO Instance()
    {
        if(instance == null)
        {
            instance = new KhoaDAO();
        }
        return instance;
    }
    // Method lấy danh sách toàn bộ khoa
    @Override public ObservableList<Khoa> getAll()
    {
        String query = "SELECT * FROM khoa";
        ObservableList<Khoa> khoaList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            while(resultSet.next())
            {
                khoaList.add(getFromResultSet(resultSet));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return khoaList;
    }

    private Khoa getFromResultSet(ResultSet resultSet) throws SQLException
    {
        Khoa khoa = new Khoa();
        khoa.setMaKhoa(resultSet.getString("MAKHOA"));
        khoa.setTenKhoa(resultSet.getString("TENKHOA"));
        khoa.setNamTL(resultSet.getString("NAMTL"));
        khoa.setPhong(resultSet.getString("PHONG"));
        khoa.setSdt(resultSet.getString("SDT"));
        khoa.setTruongKhoa(resultSet.getString("TRGKHOA"));
        khoa.setNgayNhanChuc(resultSet.getString("NGAYNC"));
        return khoa;
    }
    // Method lấy khoa theo MAKHOA
    @Override public Khoa get(String maKhoa)
    {
        String query = "SELECT * FROM khoa WHERE MAKHOA='" + maKhoa + "'";
        Khoa khoa = null;
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            if(resultSet != null)
            {
                resultSet.next();
                khoa = getFromResultSet(resultSet);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return khoa;
    }
    // Method kiểm tra khoa có tồn tại trong bảng không
    public boolean isContain(String maKhoa) throws SQLException
    {
        String query = "SELECT * FROM khoa WHERE MAKHOA='" + maKhoa + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
    // Method insert khoa vào CSDL
    @Override public void insert(Khoa khoa)
    {
        String query = "INSERT INTO khoa(MAKHOA, TENKHOA, NAMTL, PHONG, SDT, TRGKHOA, NGAYNC) VALUES"
                       + "('" + khoa.getMaKhoa() + "','" + khoa.getTenKhoa() + "','" + khoa.getNamTL() + "','"
                       + khoa.getPhong() + "','" + khoa.getSdt() + "'," + khoa.getTruongKhoa() + ",'"
                       + khoa.getNgayNhanChuc() + "')";

        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method update thông tin khoa
    @Override public void update(Khoa khoa)
    {
        String query = "UPDATE khoa SET "
                       + "TENKHOA='" + khoa.getTenKhoa() + "',"
                       + "NAMTL='" + khoa.getNamTL() + "',"
                       + "PHONG='" + khoa.getPhong() + "',"
                       + "SDT='" + khoa.getSdt() + "',"
                       + "TRGKHOA='" + khoa.getTruongKhoa() + "',"
                       + "NGAYNC='" + khoa.getNgayNhanChuc() + "'"
                       + "WHERE MAKHOA='" + khoa.getMaKhoa() + "'";


        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method xóa khoa khỏi CSDL
    @Override public void delete(String maKhoa)
    {
        String query = "DELETE FROM khoa WHERE MAKHOA='" + maKhoa + "'";

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
