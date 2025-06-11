
package Servicios;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Clase para sonido de toast tipo exito
public class ToastExito {
    private static Clip sonidoClip;

    static {
        String soundResourcePath = "/Musica/Toast Positivo.wav";
        // Usar ClickEliminar.class para ser consistentes, aunque con "/" funciona desde cualquier clase.
        URL url = ClickEliminar.class.getResource(soundResourcePath);

        System.out.println("ToastExito: Intentando cargar el recurso de sonido: " + soundResourcePath);
        System.out.println("ToastExito: URL del recurso resuelta a: " + url);

        if (url == null) {
            // Actualizar los mensajes de error para que mencionen ClickEliminar y la ruta correcta
            System.err.println("ToastExito ERROR: No se encontró el archivo de sonido '" + soundResourcePath + "'.");
            System.err.println("ToastExito INFO: Asegúrese de que 'eliminar.wav' esté en la carpeta 'Musica' en la raíz de su classpath/directorio de salida.");
        } else {
            try {
                InputStream is = url.openStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);

                sonidoClip = AudioSystem.getClip();
                sonidoClip.open(audioInputStream); // Esta es la línea 36 (aproximadamente)
                System.out.println("ToastExito: Sonido eliminar (Clip) cargado correctamente desde: " + url.toExternalForm());

            } catch (UnsupportedAudioFileException e) {
                System.err.println("ToastExito ERROR: Formato de audio no soportado para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                System.err.println("ToastExito ERROR: Línea de audio no disponible para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) { // Captura IllegalArgumentException aquí
                System.err.println("ToastExito ERROR: Error general cargando sonido eliminar '" + soundResourcePath + "'. Mensaje: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void reproducirSonidoToastExito() {
        if (sonidoClip != null) {
            if (sonidoClip.isRunning()) {
                sonidoClip.stop();
            }
            sonidoClip.setFramePosition(0);
            sonidoClip.start();
        } else {
            System.err.println("ToastExito: No se puede reproducir el sonido eliminar. El Clip no está cargado o hubo un error en la carga.");
            // Corregir la verificación para que coincida con cómo se carga y la clase actual
            if (ClickEliminar.class.getResource("/Musica/Toast Positivo.wav") == null) {
                 System.err.println("ToastExito INFO: Re-verificación indica que '/Musica/Toast Positivo.wav' sigue sin encontrarse.");
            }
        }
    }

    public static void detenerSonidoToastExito() {
        if (sonidoClip != null && sonidoClip.isRunning()) {
            sonidoClip.stop();
        }
    }

    // Opcional: Método para liberar recursos si es necesario (ej. al cerrar la aplicación)
    public static void close() {
        if (sonidoClip != null) {
            sonidoClip.close();
            System.out.println("ToastExito: Sonido click (Clip) cerrado.");
        }
    }
}
