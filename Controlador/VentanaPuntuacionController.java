
package Controlador;

import Visual.Toast;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaPuntuacionController {
    
    @FXML
    private TextField txtNombreJugador;
    @FXML
    private Label labelPuntuacion;
    @FXML
    private Button PuntuacionCerrar;
    private int puntuacion;
    
    private PantallaJuegoController juegoController;
    
    public void setJuegoController(PantallaJuegoController controller) {
        this.juegoController = controller;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion; // ← Aquí sí asignamos el valor recibido
        labelPuntuacion.setText("PUNTUACIÓN TOTAL: " + puntuacion +" PUNTOS");
    }

    
    @FXML
    private void guardarPuntuacion() {
        String nombre = txtNombreJugador.getText().trim();

        if (nombre.matches("^[a-zA-Z0-9]{3,20}$")) {
            try {
                juegoController.guardarPuntuacionEnBD(nombre, puntuacion);
                Toast.mostrar(juegoController.getStackPane(), "Puntuación registrada con éxito", "exito");

                // Mostrar leaderboard recién ahora
                juegoController.mostrarLeaderboard();

                // Cerrar la ventana de puntuación
                Stage stage = (Stage) txtNombreJugador.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                Toast.mostrar(juegoController.getStackPane(), "Error al guardar: " + e.getMessage(), "error");
            }
        } else {
            Toast.mostrar(juegoController.getStackPane(), "Nombre inválido. Debe tener 3-20 caracteres alfanuméricos.", "error");
        }
    }  
}
