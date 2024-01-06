import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Sacar {

    @FXML
    private static Label valDisp;

    @FXML
    private TextField valSaq;

    @FXML
    void sacar(ActionEvent event) {
        if(OperacoesBanco.consultarSaldo(Login.getCpfLogin(), false) > Double.parseDouble(valSaq.getText())){
            OperacoesBanco.sacar(Login.getCpfLogin(), Double.parseDouble(valSaq.getText()), false);
        }
        else{
            System.out.println("Erro");
        }
    }


    /**
     * @return Label return the valDisp
     */
    public Label getValDisp() {
        return valDisp;
    }

    /**
     * @param valDisp the valDisp to set
     */
    public static void setValDisp(String val) {
        valDisp.setText(val);;
    }

}
