import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    @Override public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("view/loginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        // try
        // {
        //     GiaoVien gv = new GiaoVien();
        //     gv.setMaGV("020");
        //     gv.setHoTen("Nguyen Quoc Dat");
        //     gv.setGioiTinh("Nam");
        //     gv.setNgaySinh("2011-01-01");
        //     gv.setDiaChi("16/3 Hoc Lac");
        //     gv.setLuong("1000");
        //     gv.setMaBM("VL");
        //     String query = "INSERT INTO giaovien(MAGV, HOTEN, GT, NGSINH, DIACHI, LUONG, MABM) VALUES"
        //                + "('" + gv.getMaGV() + "','" + gv.getHoTen() + "','" + gv.getGioiTinh() + "','"
        //                + gv.getNgaySinh() + "','" + gv.getDiaChi() + "'," + gv.getLuong()
        //                + ",'" + gv.getMaBM() + "')";
        //     DBUtil.ExecuteUpdate(query);
        // }
        // catch(Exception e)
        // {
        //     e.printStackTrace();
        // }
        launch(args);
        // String query = "SELECT * FROM giaovien";
        // try
        // {
        //     ResultSet rs = DBUtil.ExecuteQuery(query);
        //     ResultSetMetaData rsd = rs.getMetaData();
        //     int count = rsd.getColumnCount();
        //     for(int i = 1; i <= count; i++)
        //     {
        //         System.out.println(rsd.getColumnName(i));
        //     }
        // }
        // catch(Exception e)
        // {
        //     e.printStackTrace();
        // }
    }
}
