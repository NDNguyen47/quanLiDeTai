package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DeTai;
import utils.DBUtil;

public class DeTaiDAO implements DAO<DeTai> {
    private static DeTaiDAO instance;
    private DeTaiDAO()
    {
    }
    public static DeTaiDAO Instance()
    {
        if(instance == null)
        {
            instance = new DeTaiDAO();
        }
        return instance;
    }
    // Method lấy danh sách toàn bộ đề tài
    @Override public ObservableList<DeTai> getAll()
    {
        String query = "SELECT * FROM detai";
        ObservableList<DeTai> deTaiList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            while(resultSet.next())
            {
                deTaiList.add(getFromResultSet(resultSet));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return deTaiList;
    }

    private DeTai getFromResultSet(ResultSet resultSet) throws SQLException
    {
        DeTai dt = new DeTai();
        dt.setMaDT(resultSet.getString("MADT"));
        dt.setTenDT(resultSet.getString("TENDT"));
        dt.setCapQL(resultSet.getString("CAPQL"));
        dt.setKinhPhi(resultSet.getString("KINHPHI"));
        dt.setNgayBD(resultSet.getString("NGAYBD"));
        dt.setNgayKT(resultSet.getString("NGAYKT"));
        dt.setMaCD(resultSet.getString("MACD"));
        return dt;
    }
    // Method lấy đề tài theo MADT
    public ObservableList<DeTai> getFromMaGV(String maGV)
    {
        String query = "SELECT d.MADT, d.TENDT "
                        + "FROM detai d "
                        + "JOIN thamgiadt t USING(MADT) "
                        + "WHERE MAGV = '" + maGV + "' "
                        + "GROUP BY d.MADT, d.TENDT";
        ObservableList<DeTai> deTaiList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            while(resultSet.next())
            {
                DeTai dt = new DeTai();
                dt.setMaDT(resultSet.getString("MADT"));
                dt.setTenDT(resultSet.getString("TENDT"));
                deTaiList.add(dt);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return deTaiList;
    }

    @Override public DeTai get(String maDT)
    {
        String query = "SELECT * FROM detai WHERE MADT='" + maDT + "'";
        DeTai dt = null;
        try
        {
            ResultSet resultSet = DBUtil.ExecuteQuery(query);
            if(resultSet != null)
            {
                resultSet.next();
                dt = getFromResultSet(resultSet);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return dt;
    }
    // Method kiểm tra đề tài có tồn tại trong bảng không
    public boolean isContain(String maDT) throws SQLException
    {
        String query = "SELECT * FROM detai WHERE MADT='" + maDT + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
    // Method insert đề tài vào CSDL
    @Override public void insert(DeTai dt)
    {
        String query = "INSERT INTO detai(MADT, TENDT, CAPQL, KINHPHI, NGAYBD, NGAYKT, MACD) VALUES"
                       + "('" + dt.getMaDT() + "','" + dt.getTenDT() + "','" + dt.getCapQL() + "',"
                       + dt.getKinhPhi() + ",'" + dt.getNgayBD() + "','" + dt.getNgayKT()
                       + "'','" + dt.getMaCD() + "')";

        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method update thông tin đề tài
    @Override public void update(DeTai dt)
    {
        String query = "UPDATE detai SET "
                       + "TENDT='" + dt.getTenDT() + "',"
                       + "CAPQL='" + dt.getCapQL() + "',"
                       + "KINHPHI=" + dt.getKinhPhi() + ","
                       + "NGAYBD='" + dt.getNgayBD() + "',"
                       + "NGAYKT='" + dt.getNgayKT() + "',"
                       + "MACD='" + dt.getMaCD() + "'"
                       + "WHERE MADT='" + dt.getMaDT() + "'";


        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Method xóa đề tài khỏi CSDL
    @Override public void delete(String maDT)
    {
        String query = "DELETE FROM detai WHERE MADT='" + maDT + "'";

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
