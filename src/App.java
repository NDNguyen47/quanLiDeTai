import dao.GiaoVienDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GiaoVien;

public class App extends Application
{
    @Override public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("view/DashBoard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        // launch(args);
        try
        {
            ObservableList<GiaoVien> listGV = GiaoVienDAO.Instance().getAll();
            for(GiaoVien gv : listGV)
            {
                System.out.println(gv.getMaGV() + "," + gv.getHoTen());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
