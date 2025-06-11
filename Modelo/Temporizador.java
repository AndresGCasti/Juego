package Modelo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Temporizador {

    private Timeline timeline;
    private int tiempoRestante;
    private Runnable onTiempoTerminado; // Callback cuando el tiempo llegue a 0
    private Runnable onTiempoActualizado; // Callback para actualizar la UI

    public Temporizador(int segundos, Runnable onTiempoTerminado, Runnable onTiempoActualizado) {
        this.tiempoRestante = segundos;
        this.onTiempoTerminado = onTiempoTerminado;
        this.onTiempoActualizado = onTiempoActualizado;
    }

    public void iniciar() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (tiempoRestante > 0) {
                tiempoRestante--;
                if (onTiempoActualizado != null) {
                    onTiempoActualizado.run(); // Notificar actualización
                }
            } else {
                detener();
                if (onTiempoTerminado != null) {
                    onTiempoTerminado.run(); // Notificar que el tiempo terminó
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void detener() {
        if (timeline != null) {
            timeline.stop();
            System.out.println("Temporizador detenido");
        }
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
    
}
