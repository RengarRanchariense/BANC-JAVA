import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Depositar {

    @FXML
    private TextField cpfDest;

    @FXML
    private TextField valorDest;

    @FXML
    void btnProcess(ActionEvent event) {
        if(Double.parseDouble(valorDest.getText()) > 0){
            OperacoesBanco.depositarContaCorrente(cpfDest.getText(), Double.parseDouble(valorDest.getText()));
        }
        
    }

}
