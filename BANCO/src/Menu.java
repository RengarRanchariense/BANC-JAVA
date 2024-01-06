import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Menu {

    @FXML
    private AnchorPane arcPane;

    @FXML
    private BorderPane bordPane;

    @FXML
    private Label lbValCc;

    @FXML
    void btnCc(MouseEvent event) {
        double valor = OperacoesBanco.consultarSaldo(Login.getCpfLogin(), false);
        bordPane.setCenter(arcPane);
        lbValCc.setText(String.valueOf(valor));

    }

    @FXML
    void btnCp(MouseEvent event) {
        double valor = OperacoesBanco.consultarSaldo(Login.getCpfLogin(), true);
        PgPoup.setLbValPp(String.valueOf(valor));
        loadPage("PgPoup");

    }

    @FXML
    void btnDepo(MouseEvent event) {
        loadPage("Depositar");

    }

    @FXML
    void btnSacar(MouseEvent event) {
        double valor = OperacoesBanco.consultarSaldo(Login.getCpfLogin(), false);
        Sacar.setValDisp(String.valueOf(valor));
        loadPage("Sacar");

    }

    @FXML
    void btnSair(MouseEvent event) {
        App.login(2);

    }



    private void loadPage(String page){
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        bordPane.setCenter(root);
    }

    

    


    

}
