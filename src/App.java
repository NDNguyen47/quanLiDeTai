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
        launch(args);
        // try
        // {
        //     User user = new User(
        //         "admin",
        //         "123456",
        //         "What is your favorite color?",
        //         "red",
        //         "2023-12-09"
        //     );
        //     UserDAO.Instance().insert(user);
        // }
        // catch(Exception e)
        // {
        //     e.printStackTrace();
        // }
    }
}
