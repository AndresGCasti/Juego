package Controlador;

import Servicios.MusicaClick;
import Modelo.PalabraPuntaje;
import Modelo.LetrasAleatorias;
import Modelo.RepositorioLetras;
import Modelo.Temporizador;
import Repositorios.ValidadorPalabraBD;
import Repositorios.ConeccionBD;
import Servicios.ClickEliminar;
import Servicios.SonidoPop;
import Visual.Toast;
import java.sql.Connection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class PantallaJuegoController implements Initializable {
    
    @FXML
    private Button btnAtras;
    @FXML
    public Button bto1x1;
    @FXML
    private Button bto1x2;
    @FXML
    private Button bto1x3;
    @FXML
    private Button bto1x4;
    @FXML
    private Button bto1x5;
    @FXML
    private Button bto1x6;
    @FXML
    private Button bto2x1;
    @FXML
    private Button bto2x2;
    @FXML
    private Button bto2x3;
    @FXML
    private Button bto2x4;
    @FXML
    private Button bto2x5;
    @FXML
    private Button bto2x6;
    @FXML
    private Button bto3x1;
    @FXML
    private Button bto3x2;
    @FXML
    private Button bto3x3;
    @FXML
    private Button bto3x4;
    @FXML
    private Button bto3x5;
    @FXML
    private Button bto3x6;
    @FXML
    private Button bto4x1;
    @FXML
    private Button bto4x2;
    @FXML
    private Button bto4x3;
    @FXML
    private Button bto4x4;
    @FXML
    private Button bto4x5;
    @FXML
    private Button bto4x6;
    @FXML
    private Button bto5x1;
    @FXML
    private Button bto5x2;
    @FXML
    private Button bto5x3;
    @FXML
    private Button bto5x4;
    @FXML
    private Button bto5x5;
    @FXML
    private Button bto5x6;
    @FXML
    private Button bto6x1;
    @FXML
    private Button bto6x2;
    @FXML
    private Button bto6x3;
    @FXML
    private Button bto6x4;
    @FXML
    private Button bto6x5;
    @FXML
    private Button bto6x6;

    @FXML
    public Label labelLetras;

    private Temporizador temporizador;

    private Map<Button, Integer> contadorClicks = new HashMap<>();

    private int PuntuacionTotal;
    @FXML
    private TableView<PalabraPuntaje> tablaPuntajes;

    @FXML
    private TableColumn<PalabraPuntaje, String> tabPalabra;
    
    @FXML
    private TableColumn<PalabraPuntaje, Integer> tabPuntos;

    ObservableList<PalabraPuntaje> datosTabla = FXCollections.observableArrayList();

    @FXML
    private Button btnVerificar;
    @FXML
    private Label reloj;
    @FXML
    private Pane panePrincipalJ;
    @FXML
    private Pane PanelBotonesJ;
    @FXML
    private Button btneliminar;
    
    private Connection conexion;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LetrasAleatorias letras = new LetrasAleatorias();

        bto1x1.setText(String.valueOf(letras.LetrasBoron()));
        bto1x2.setText(String.valueOf(letras.LetrasBoron()));
        bto1x3.setText(String.valueOf(letras.LetrasBoron()));
        bto1x4.setText(String.valueOf(letras.LetrasBoron()));
        bto1x5.setText(String.valueOf(letras.LetrasBoron()));
        bto1x6.setText(String.valueOf(letras.LetrasBoron()));

        bto2x1.setText(String.valueOf(letras.LetrasBoron()));
        bto2x2.setText(String.valueOf(letras.LetrasBoron()));
        bto2x3.setText(String.valueOf(letras.LetrasBoron()));
        bto2x4.setText(String.valueOf(letras.LetrasBoron()));
        bto2x5.setText(String.valueOf(letras.LetrasBoron()));
        bto2x6.setText(String.valueOf(letras.LetrasBoron()));

        bto3x1.setText(String.valueOf(letras.LetrasBoron()));
        bto3x2.setText(String.valueOf(letras.LetrasBoron()));
        bto3x3.setText(String.valueOf(letras.LetrasBoron()));
        bto3x4.setText(String.valueOf(letras.LetrasBoron()));
        bto3x5.setText(String.valueOf(letras.LetrasBoron()));
        bto3x6.setText(String.valueOf(letras.LetrasBoron()));

        bto4x1.setText(String.valueOf(letras.LetrasBoron()));
        bto4x2.setText(String.valueOf(letras.LetrasBoron()));
        bto4x3.setText(String.valueOf(letras.LetrasBoron()));
        bto4x4.setText(String.valueOf(letras.LetrasBoron()));
        bto4x5.setText(String.valueOf(letras.LetrasBoron()));
        bto4x6.setText(String.valueOf(letras.LetrasBoron()));

        bto5x1.setText(String.valueOf(letras.LetrasBoron()));
        bto5x2.setText(String.valueOf(letras.LetrasBoron()));
        bto5x3.setText(String.valueOf(letras.LetrasBoron()));
        bto5x4.setText(String.valueOf(letras.LetrasBoron()));
        bto5x5.setText(String.valueOf(letras.LetrasBoron()));
        bto5x6.setText(String.valueOf(letras.LetrasBoron()));

        bto6x1.setText(String.valueOf(letras.LetrasBoron()));
        bto6x2.setText(String.valueOf(letras.LetrasBoron()));
        bto6x3.setText(String.valueOf(letras.LetrasBoron()));
        bto6x4.setText(String.valueOf(letras.LetrasBoron()));
        bto6x5.setText(String.valueOf(letras.LetrasBoron()));
        bto6x6.setText(String.valueOf(letras.LetrasBoron()));
        
        tabPalabra.setCellValueFactory(new PropertyValueFactory<>("palabra"));
        tabPuntos.setCellValueFactory(new PropertyValueFactory<>("puntaje"));
        tablaPuntajes.setItems(datosTabla);
        tablaPuntajes.setPlaceholder(new Label(""));

        temporizador = new Temporizador(120, this::terminarJuego, this::actualizarReloj);
        temporizador.iniciar();
        PuntuacionTotal = 0;
    }

    @FXML
    public void Atras(ActionEvent event) {
        SonidoPop.reproducirSonidoPop();
        // Desconectar de la base de datos
        terminar_volvio_atras();
        exit();
    }
     @FXML
     private void EliminarPalabra(ActionEvent event) {
        ClickEliminar.reproducirSonidoEliminar();
           // Limpiar el texto del Label
        labelLetras.setText("");

        // Recorrer todos los botones que están en el mapa
        for (Map.Entry<Button, Integer> entry : contadorClicks.entrySet()) {
            Button boton = entry.getKey();
            int estado = entry.getValue();

            // Si el botón estaba "activado", lo desactivamos visualmente
            if (estado == 1) {
                boton.getStyleClass().remove("activo"); // Quitar estilo visual
                contadorClicks.put(boton, 0); // Marcar como no presionado
            }   
        }
    }
    

    @FXML
    private void Letra1X1(ActionEvent event) {
        manejarClic(bto1x1);

    }

    @FXML
    private void Letra1X2(ActionEvent event) {
        manejarClic(bto1x2);

    }

    @FXML
    private void Letra1X3(ActionEvent event) {
        manejarClic(bto1x3);

    }

    @FXML
    private void Letra1X4(ActionEvent event) {
        manejarClic(bto1x4);
    }

    @FXML
    private void Letra1X5(ActionEvent event) {
        manejarClic(bto1x5);
    }

    @FXML
    private void Letra1X6(ActionEvent event) {
        manejarClic(bto1x6);
    }

    @FXML
    private void Letra2X1(ActionEvent event) {
        manejarClic(bto2x1);
    }

    @FXML
    private void Letra2X2(ActionEvent event) {
        manejarClic(bto2x2);
    }

    @FXML
    private void Letra2X3(ActionEvent event) {
        manejarClic(bto2x3);
    }

    @FXML
    private void Letra2X4(ActionEvent event) {
        manejarClic(bto2x4);
    }

    @FXML
    private void Letra2X5(ActionEvent event) {
        manejarClic(bto2x5);
    }

    @FXML
    private void Letra2X6(ActionEvent event) {
        manejarClic(bto2x6);
    }

    @FXML
    private void Letra3X1(ActionEvent event) {
        manejarClic(bto3x1);
    }

    @FXML
    private void Letra3X2(ActionEvent event) {
        manejarClic(bto3x2);
    }

    @FXML
    private void Letra3X3(ActionEvent event) {
        manejarClic(bto3x3);
    }

    @FXML
    private void Letra3X4(ActionEvent event) {
        manejarClic(bto3x4);
    }

    @FXML
    private void Letra3X5(ActionEvent event) {
        manejarClic(bto3x5);
    }

    @FXML
    private void Letra3X6(ActionEvent event) {
        manejarClic(bto3x6);
    }

    @FXML
    private void Letra4X1(ActionEvent event) {
        manejarClic(bto4x1);
    }

    @FXML
    private void Letra4X2(ActionEvent event) {
        manejarClic(bto4x2);
    }

    @FXML
    private void Letra4X3(ActionEvent event) {
        manejarClic(bto4x3);
    }

    @FXML
    private void Letra4X4(ActionEvent event) {
        manejarClic(bto4x4);
    }

    @FXML
    private void Letra4X5(ActionEvent event) {
        manejarClic(bto4x5);
    }

    @FXML
    private void Letra4X6(ActionEvent event) {
        manejarClic(bto4x6);
    }

    @FXML
    private void Letra5X1(ActionEvent event) {
        manejarClic(bto5x1);
    }

    @FXML
    private void Letra5X2(ActionEvent event) {
        manejarClic(bto5x2);
    }

    @FXML
    private void Letra5X3(ActionEvent event) {
        manejarClic(bto5x3);
    }

    @FXML
    private void Letra5X4(ActionEvent event) {
        manejarClic(bto5x4);
    }

    @FXML
    private void Letra5X5(ActionEvent event) {
        manejarClic(bto5x5);
    }

    @FXML
    private void Letra5X6(ActionEvent event) {
        manejarClic(bto5x6);
    }

    @FXML
    private void Letra6X1(ActionEvent event) {
        manejarClic(bto6x1);
    }

    @FXML
    private void Letra6X2(ActionEvent event) {
        manejarClic(bto6x2);
    }

    @FXML
    private void Letra6X3(ActionEvent event) {
        manejarClic(bto6x3);
    }

    @FXML
    private void Letra6X4(ActionEvent event) {
        manejarClic(bto6x4);
    }

    @FXML
    private void Letra6X5(ActionEvent event) {
        manejarClic(bto6x5);
    }

    @FXML
    private void Letra6X6(ActionEvent event) {
        manejarClic(bto6x6);
    }

    private void manejarClic(Button boton) {
        
        MusicaClick.reproducirSonidoClick();
      
        String letraBoton = boton.getText(); // Obtener la letra del botón
        String textoLabel = labelLetras.getText(); // Obtener el texto actual del Label

        // Verificar si el botón ya fue presionado antes
        if (contadorClicks.getOrDefault(boton, 0) == 0) {
            // Agregar la letra y actualizar el contador
            textoLabel += letraBoton;
            contadorClicks.put(boton, 1);
            boton.getStyleClass().add("activo"); // Agregar efecto visual
        } else {
            // Eliminar solo la primera aparición de la letra y reiniciar el contador
            textoLabel = textoLabel.replaceFirst(letraBoton, "");
            contadorClicks.put(boton, 0);
            boton.getStyleClass().remove("activo"); // Quitar efecto visual
        }

        labelLetras.setText(textoLabel); // Actualizar el texto del Label

    }
    @FXML
    private StackPane stackPrincipal; 
    private Set<String> palabrasUsadas = new HashSet<>();
    

    @FXML
    private void VerificarPalabra(ActionEvent event) {
        String palabra = labelLetras.getText(); 
        ValidadorPalabraBD verificar = new ValidadorPalabraBD();
        // Verifica la palabra en la base de datos
        int resultado = verificar.verificarPalabra(palabra,stackPrincipal,palabrasUsadas);

        if (resultado > 0) {  // Si la palabra es válida
            datosTabla.add(new PalabraPuntaje(palabra, resultado));
            // Restablece el color de los botones que fueron presionados
            for (Button boton : contadorClicks.keySet()) {
                boton.getStyleClass().remove("activo"); // Elimina la clase activa
                boton.setStyle(""); // Restablece el estilo predeterminado
            }
            PuntuacionTotal += resultado;
            contadorClicks.clear(); // Reinicia el contador de clics
        } else {
            for (Button boton : contadorClicks.keySet()) {
                boton.getStyleClass().remove("activo"); // Elimina la clase activa
                boton.setStyle(""); // Restablece el estilo predeterminado
            }
            contadorClicks.clear(); // Reinicia el contador de clics
        }
        labelLetras.setText("");
    }

    private void actualizarReloj() {
        int minutos = temporizador.getTiempoRestante() / 60;
        int segundos = temporizador.getTiempoRestante() % 60;
        reloj.setText(String.format("%d:%02d", minutos, segundos));
    }
    private void terminar_volvio_atras(){
        ConeccionBD.desconectar(ValidadorPalabraBD.conexionActiva);
        temporizador.detener();
    }
    private Stage stagePrincipal;

    public void setStagePrincipal(Stage stage) {
        this.stagePrincipal = stage;
    }

    private Scene scenePantallaPrincipal;

    public void setScenePantallaPrincipal(Scene scene) {
        this.scenePantallaPrincipal = scene;
    }
    public void exit() {
    if (stagePrincipal != null && scenePantallaPrincipal != null) {
        Parent currentRoot = stagePrincipal.getScene().getRoot();

        // Animación de salida
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(500), currentRoot);
        scaleOut.setFromX(1);
        scaleOut.setFromY(1);
        scaleOut.setToX(0);
        scaleOut.setToY(0);
        
        scaleOut.setOnFinished(e -> {
            // Crear StackPane temporal solo con fondo
            StackPane fondoTransicion = new StackPane();
            fondoTransicion.setStyle("-fx-background-color: #1e1e1e;");
            Scene escenaTemporal = new Scene(fondoTransicion);
            stagePrincipal.setScene(escenaTemporal);

            // Esperar un frame para que JavaFX pinte el fondo
            Platform.runLater(() -> {
                // Obtener root de la escena principal
                Parent originalRoot = scenePantallaPrincipal.getRoot();
                originalRoot.setScaleX(0);
                originalRoot.setScaleY(0);

                // Envolver en StackPane con fondo
                StackPane fondoFinal = new StackPane();
                fondoFinal.setStyle("-fx-background-color: #1e1e1e;");
                fondoFinal.getChildren().add(originalRoot);

                // Reemplazar escena completa por la definitiva
                Scene nuevaScene = new Scene(fondoFinal);
                stagePrincipal.setScene(nuevaScene);

                // Iniciar animación de entrada
                ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), originalRoot);
                scaleIn.setFromX(0);
                scaleIn.setFromY(0);
                scaleIn.setToX(1);
                scaleIn.setToY(1);
                scaleIn.play();
            });
        });

        scaleOut.play();
    }
}
    
    public void mostrarVentanaPuntuacion(int puntuacionTotal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/visual/VentanaPuntuacion.fxml"));
            Parent root = loader.load();

            // Obtengo el controlador de esa ventana
            VentanaPuntuacionController controller = loader.getController();
            controller.setPuntuacion(puntuacionTotal);
            controller.setJuegoController(this); // ← esto faltaba ✅
            
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            
            FadeTransition fade = new FadeTransition(Duration.millis(500), root);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.play();

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);

            stage.show();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void guardarPuntuacionEnBD(String nombre, int puntaje) {
        String sql = "INSERT INTO jugadores (nombre, puntaje) VALUES (?, ?)";
        try (Connection conn = ConeccionBD.conectar();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, puntaje);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el puntaje: " + e.getMessage());
        }
    }

        public StackPane getStackPane() {
        return stackPrincipal;
    }

    public void mostrarLeaderboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Visual/leaderboard.fxml"));
            Parent root = fxmlLoader.load();

            LeaderboardController leaderboardController = fxmlLoader.getController();
            leaderboardController.setPantallaJuegoController(this);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);

            stage.show();

            FadeTransition fade = new FadeTransition(Duration.millis(500), root);
            fade.setFromValue(0);
            fade.setToValue(1);

            ScaleTransition scale = new ScaleTransition(Duration.millis(500), root);
            scale.setFromX(0);
            scale.setFromY(0);
            scale.setToX(1);
            scale.setToY(1);

            ParallelTransition animacion = new ParallelTransition(fade, scale);
            animacion.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(" " + ex);
            alert.showAndWait();
        }
    }
      
    private void terminarJuego() {
        ConeccionBD.desconectar(ValidadorPalabraBD.conexionActiva);
        temporizador.detener();
        mostrarVentanaPuntuacion(PuntuacionTotal);
        System.out.println("Mi puntuación total fue: " + PuntuacionTotal);
        PuntuacionTotal = 0;
    }
    private Scene miEscena;

    public void setSceneJuego(Scene scene) {
        this.miEscena = scene;
    }

    public Scene getSceneJuego() {
        return this.miEscena;
    }
    
    public Stage getStagePrincipal() {
    return this.stagePrincipal;
    }

    
    public void reiniciarJuego() {
        // Reiniciar letras, temporizador, puntaje, tabla y label
   RepositorioLetras letras = new RepositorioLetras();

        Button[] botones = {
            bto1x1, bto1x2, bto1x3, bto1x4, bto1x5, bto1x6,
            bto2x1, bto2x2, bto2x3, bto2x4, bto2x5, bto2x6,
            bto3x1, bto3x2, bto3x3, bto3x4, bto3x5, bto3x6,
            bto4x1, bto4x2, bto4x3, bto4x4, bto4x5, bto4x6,
            bto5x1, bto5x2, bto5x3, bto5x4, bto5x5, bto5x6,
            bto6x1, bto6x2, bto6x3, bto6x4, bto6x5, bto6x6
        };

        for (Button btn : botones) {
            btn.setText(String.valueOf(letras.LetrasBoron()));
            btn.getStyleClass().remove("activo");
            btn.setStyle("");
        }

        contadorClicks.clear();
        labelLetras.setText("");
        datosTabla.clear();
        palabrasUsadas.clear();

        if (temporizador != null) {
            temporizador.detener();
        }

        temporizador = new Temporizador(180, this::terminarJuego, this::actualizarReloj);
        temporizador.iniciar();
        PuntuacionTotal = 0;
    }
}
