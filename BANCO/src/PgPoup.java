import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PgPoup {

    @FXML
    private static Label lbValPp;

    


    /**
     * @return Label return the lbValPp
     */
    public Label getLbValPp() {
        return lbValPp;
    }

    /**
     * @param lbValPp the lbValPp to set
     */
    public static void setLbValPp(String lb) {
        lbValPp.setText(lb);
    }

}
