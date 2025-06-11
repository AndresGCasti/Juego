
package Controlador;

import Servicios.SonidoPop;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class ReglasController implements Initializable {

    @FXML
    private Button btncerrarreglas;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarreglas(ActionEvent event) {
        SonidoPop.reproducirSonidoPop();
        Stage stage = (Stage) ((Node)this.btncerrarreglas).getScene().getWindow();
        stage.close();
    }
  }
    

