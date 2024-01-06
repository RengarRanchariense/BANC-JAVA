import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Cadastro {

    @FXML
    private TextField cpfCad;

    @FXML
    private TextField nameCad;

    @FXML
    private PasswordField pswCad;

    @FXML
    void btnCad(ActionEvent event) {
        OperacoesBanco.cadastrarCliente(nameCad.getText(),cpfCad.getText(), pswCad.getText());
        App.login(2);

    }

}
