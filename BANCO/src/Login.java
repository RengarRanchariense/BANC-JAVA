import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Login {

    @FXML
    private static TextField cpfLogin;

    @FXML
    private PasswordField pswLogin;

    @FXML
    void cadUsers(MouseEvent event) {
        App.login(3);

    }

    @FXML
    void loginUser(ActionEvent event) {
        if(OperacoesBanco.realizarLogin(cpfLogin.getText(), pswLogin.getText()))
            App.login(1);
        else{
            System.out.println("Erro ao realizar loguin");
        }

    }


    /**
     * @return TextField return the cpfLogin
     */
    public static String getCpfLogin() {
        return cpfLogin.getText();
    }


}
