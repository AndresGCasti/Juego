package Visual;
import Servicios.ToastAd;
import Servicios.ToastError;
import Servicios.ToastExito;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Toast {
      public static void mostrar(StackPane root, String mensaje, String tipo) {
        Label toast = new Label(mensaje);
        toast.setFont(new Font("Kanit", 20));
        toast.setTextFill(Color.WHITE);
        toast.setStyle("-fx-background-radius: 15;"
                +      "-fx-padding: 10 20 10 20; ");

        StackPane.setMargin(toast, new Insets(200, 0, 60, 600));  // top, right, bottom, left      

        switch (tipo.toLowerCase()) {
            case "exito":
                ToastExito.reproducirSonidoToastExito();
                toast.setStyle(toast.getStyle() + "-fx-background-color: #28a745;");
                break;
            case "error":
                ToastError.reproducirSonidoToastError();
                toast.setStyle(toast.getStyle() + "-fx-background-color: #dc3545;");
                break;
            case "advertencia":
                ToastAd.reproducirSonidoToastAd();
                toast.setStyle(toast.getStyle() + "-fx-background-color: #ffc107; -fx-text-fill: black;");
                break;
            default:
                toast.setStyle(toast.getStyle() + "-fx-background-color: #17a2b8;");
        }

        root.getChildren().add(toast);
        // Creamos una transición de desvanecimiento (FadeTransition) que durará 4 segundos y se aplicará al Label 'toast'
        FadeTransition fade = new FadeTransition(Duration.seconds(4), toast);
        // Establecemos que el toast comience con opacidad completa (visible)
        fade.setFromValue(1.0);
        // Establecemos que el toast termine completamente transparente (invisible)
        fade.setToValue(0.0);
        // Cuando termine la animación, eliminamos el toast del StackPane para que no quede ocupando memoria o espacio
        fade.setOnFinished(e -> root.getChildren().remove(toast));
        // Ejecutamos la animación
        fade.play();
      }
      
}
