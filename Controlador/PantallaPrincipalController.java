package Controlador;

import Servicios.MusicaGlobal;
import Servicios.SonidoPop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PantallaPrincipalController implements Initializable {
    
    private StackPane rootPane;
    @FXML
    private Button btncomojugar;
    @FXML
    private Button btnJugar;
    @FXML
    private Pane panePrincipal;
    @FXML
    private Label txtHolyPixel;
    @FXML
    private Label txtHoly;
    @FXML
    private Label txtpixel;
    @FXML
    private ImageView imaglogo;
    @FXML
    private Button Musica;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MusicaGlobal.iniciarMusica();
        
    }

   @FXML
    private void AbrirVentana(ActionEvent event) throws IOException {
        try {
            SonidoPop.reproducirSonidoPop();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Visual/PantallaJuego.fxml"));
            Parent root = loader.load();

            PantallaJuegoController juegoController = loader.getController();
            StackPane rootWrapper = new StackPane();
            rootWrapper.setStyle("-fx-background-image: url('/imagenes/Fondo.png');"
                               + "-fx-background-size: cover;"
                               + "-fx-background-repeat: no-repeat;"
                               + "-fx-background-position: center center;");
            root.setOpacity(0); // 🔁 Ocultar al inicio
            rootWrapper.getChildren().add(root);

            Scene sceneJuego = new Scene(rootWrapper);

            // Guardar escena y stage actual
            juegoController.setScenePantallaPrincipal(((Node) event.getSource()).getScene());
            juegoController.setStagePrincipal((Stage)((Node) event.getSource()).getScene().getWindow());
            juegoController.setSceneJuego(sceneJuego);

            Stage stage = (Stage) ((Node) this.btnJugar).getScene().getWindow();
            stage.setScene(sceneJuego);  // 🔁 Cambiar escena antes de mostrar

            // Preparar animaciones
            FadeTransition fadeIn = new FadeTransition(Duration.millis(600), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            TranslateTransition slideIn = new TranslateTransition(Duration.millis(600), root);
            slideIn.setFromX(stage.getWidth());
            slideIn.setToX(0);
            slideIn.setInterpolator(Interpolator.EASE_IN);

            ParallelTransition animacion = new ParallelTransition(fadeIn, slideIn);
            animacion.play();

            // Configuración adicional
            stage.setTitle("WordFamae");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/Logo.png")));

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING); 
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(" " + ex);
            alert.showAndWait();
        }
    }


    @FXML
    private void ventanacomojugar(ActionEvent event) {
        try {
            SonidoPop.reproducirSonidoPop();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Visual/Reglas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT); // ✅ Ventana sin bordes ni fondo
            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT); // ✅ Fondo transparente de la escena
            stage.setScene(scene);

            // Animación
            ScaleTransition st = new ScaleTransition(Duration.millis(900), root);
            st.setFromX(0);
            st.setFromY(0);
            st.setToX(1);
            st.setToY(1);
            st.play();

            stage.show();

        } catch (IOException ex) {
            Alert alert =  new Alert (Alert.AlertType.WARNING); 
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(" "+ ex );
            alert.showAndWait();
        }      
    }
    @FXML
    private ImageView iconoMusica;

    private boolean musicaActiva = true; // Estado actual de la música
    @FXML
    private void PlayPause(ActionEvent event) {
        SonidoPop.reproducirSonidoPop();
        if (musicaActiva) {
            MusicaGlobal.pausarReanudar(); // Pausar
            iconoMusica.setImage(new Image(getClass().getResource("/imagenes/volume_off.png").toExternalForm()));
            musicaActiva = false;
        } else {
            MusicaGlobal.pausarReanudar(); // Reanudar
            iconoMusica.setImage(new Image(getClass().getResource("/imagenes/volume_on.png").toExternalForm()));
            musicaActiva = true;
        }    
    }
}
