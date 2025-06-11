
package Controlador;

import Modelo.Jugador;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class LeaderboardController implements Initializable {
    
    private PantallaJuegoController pantallaJuegoController;
    @FXML
    private Pane panelpuntaje;
    @FXML
    private VBox leaderboardContainer;
    @FXML
    private Button btnsalirpuntaje;
    @FXML
    private Button btncomenzar;

    private AnchorPane AnchorPane1;
    @FXML
    private Pane panelTitulo;
    @FXML
    private Label lbleader;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<PlayerScore> scores = new ArrayList<>();

        // 🔁 Llamar directamente al repositorio
    List<Jugador> jugadores = Repositorios.JugadoresTablaBD.obtenerTopJugadoresDesdeBD();
    for (Jugador jugador : jugadores) {
        scores.add(new PlayerScore(jugador.getNombre(), jugador.getPuntaje()));
    }

        // --- Mostrar en la interfaz ---
        for (int i = 0; i < scores.size(); i++) {
            PlayerScore score = scores.get(i);

            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(2));

            Label name = new Label();
            Label points = new Label(String.valueOf(score.getScore()));
            Region separador = new Region();
            separador.setPrefWidth(90);
            Region separador1 = new Region();
            separador1.setPrefWidth(1);

            String backgroundColor;
            String textColor;
            String emoji = "";
            Color glowColor;

            switch (i) {
                case 0: // 🥇 Oro
                    backgroundColor = "#FFD700";
                    textColor = "black";
                    emoji = "🏆 ";
                    glowColor = Color.GOLD;
                    break;
                case 1: // 🥈 Plata
                    backgroundColor = "#C0C0C0";
                    textColor = "black";
                    emoji = "🥈 ";
                    glowColor = Color.SILVER;
                    break;
                case 2: // 🥉 Bronce
                    backgroundColor = "#CD7F32";
                    textColor = "white";
                    emoji = "🥉 ";
                    glowColor = Color.ORANGE;
                    break;
                default:
                    backgroundColor = "#ff9800";
                    textColor = "white";
                    glowColor = null;
            }

            row.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 15;");
            name.setText(emoji + score.getName());
            name.setStyle("-fx-text-fill: " + textColor + "; -fx-font-size: 16px; -fx-font-family: Kanit;" + (i < 3 ? " -fx-font-weight: bold;" : ""));
            name.setPrefWidth(200);

            points.setStyle("-fx-text-fill: " + textColor + "; -fx-font-size: 16px; -fx-font-family: Kanit;" + (i < 3 ? " -fx-font-weight: bold;" : ""));

            if (i < 3) {
                DropShadow glow = new DropShadow();
                glow.setColor(glowColor);
                glow.setRadius(10);
                row.setEffect(glow);

                Timeline glowAnim = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(glow.radiusProperty(), 10)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(glow.radiusProperty(), 20)),
                    new KeyFrame(Duration.seconds(2), new KeyValue(glow.radiusProperty(), 10))
                );
                glowAnim.setCycleCount(Timeline.INDEFINITE);
                glowAnim.play();
            }

            row.getChildren().addAll(separador1, name, separador, points);
            leaderboardContainer.getChildren().add(row);
        }

    }
    
    public void setPantallaJuegoController(PantallaJuegoController pantallaJuegoController) {
    this.pantallaJuegoController = pantallaJuegoController;
    }

    @FXML
    private void BtnExit(ActionEvent event) {
        Parent root = btnsalirpuntaje.getScene().getRoot();
        // Animación de desvanecimiento (fade out)
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), root);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            if (pantallaJuegoController != null) {
                pantallaJuegoController.exit(); 
            }
            btnsalirpuntaje.getScene().getWindow().hide();
        });

        fadeTransition.play();
    }
    
   
    @FXML
private void BtnNuevoJuego(ActionEvent event) {
    if (pantallaJuegoController != null) {
        // Animación de salida (zoom out + fade out)
        Parent actualRoot = btncomenzar.getScene().getRoot();
        ScaleTransition zoomOut = new ScaleTransition(Duration.millis(300), actualRoot);
        zoomOut.setFromX(1);
        zoomOut.setFromY(1);
        zoomOut.setToX(0.8);
        zoomOut.setToY(0.8);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), actualRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        ParallelTransition salida = new ParallelTransition(zoomOut, fadeOut);

        salida.setOnFinished(e -> {
            pantallaJuegoController.reiniciarJuego();

            Stage leaderboardStage = (Stage) btncomenzar.getScene().getWindow();
            leaderboardStage.close();

            Parent juegoRoot = pantallaJuegoController.getSceneJuego().getRoot();
            juegoRoot.setOpacity(0);
            juegoRoot.setScaleX(0.8);
            juegoRoot.setScaleY(0.8);

            Stage stage = pantallaJuegoController.getStagePrincipal();
            stage.setScene(pantallaJuegoController.getSceneJuego());

            // Animación de entrada (fade in + zoom in)
            FadeTransition fadeIn = new FadeTransition(Duration.millis(400), juegoRoot);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            ScaleTransition zoomIn = new ScaleTransition(Duration.millis(400), juegoRoot);
            zoomIn.setFromX(0.8);
            zoomIn.setFromY(0.8);
            zoomIn.setToX(1.05); // ← un poco más grande para el rebote
            zoomIn.setToY(1.05);

            ParallelTransition entrada = new ParallelTransition(fadeIn, zoomIn);

            entrada.setOnFinished(event2 -> {
                // 🎯 Animación de rebote al final
                ScaleTransition rebote = new ScaleTransition(Duration.millis(150), juegoRoot);
                rebote.setFromX(1.05);
                rebote.setFromY(1.05);
                rebote.setToX(1.0);
                rebote.setToY(1.0);
                rebote.play();
            });

            entrada.play();
            stage.show();
        });

        salida.play();
    }
}

    // Clase auxiliar interna
    public static class PlayerScore {
        private String name;
        private int score;

        public PlayerScore(String name, int score) {
            this.name = name;
            this.score = score;
        }
        public String getName() { return name; }
        public int getScore() { return score; }
    }
}
