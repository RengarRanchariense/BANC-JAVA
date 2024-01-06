import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Stage stage;
    private static Scene loginScene;
    private static Scene menuScene;
    private static Scene cadUser;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("Login.fxml"));
        loginScene = new Scene(fxmlLogin);
        Parent fxmlMenu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        menuScene = new Scene(fxmlMenu);
        Parent cadMenu = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
        cadUser = new Scene(cadMenu);
        primaryStage.setTitle("Banco Java");
        primaryStage.setResizable(false);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void login(int i){
        switch (i) {
            case 1:
                stage.setScene(menuScene);
                break;
            case 2:
                stage.setScene(loginScene);
                break;
            case 3:
                stage.setScene(cadUser);
                break;
            default:
                break;
        }
    }
}
