package Servicios;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

//Clase para boton de eliminar palabra
public class ClickEliminar {
    
    private static Clip sonidoClip;

    static {
        String soundResourcePath = "/Musica/eliminar.wav";
        // Usar ClickEliminar.class para ser consistentes, aunque con "/" funciona desde cualquier clase.
        URL url = ClickEliminar.class.getResource(soundResourcePath);

        System.out.println("ClickEliminar: Intentando cargar el recurso de sonido: " + soundResourcePath);
        System.out.println("ClickEliminar: URL del recurso resuelta a: " + url);

        if (url == null) {
            // Actualizar los mensajes de error para que mencionen ClickEliminar y la ruta correcta
            System.err.println("ClickEliminar ERROR: No se encontró el archivo de sonido '" + soundResourcePath + "'.");
            System.err.println("ClickEliminar INFO: Asegúrese de que 'eliminar.wav' esté en la carpeta 'Musica' en la raíz de su classpath/directorio de salida.");
        } else {
            try {
                InputStream is = url.openStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);

                sonidoClip = AudioSystem.getClip();
                sonidoClip.open(audioInputStream); // Esta es la línea 36 (aproximadamente)
                System.out.println("ClickEliminar: Sonido eliminar (Clip) cargado correctamente desde: " + url.toExternalForm());

            } catch (UnsupportedAudioFileException e) {
                System.err.println("ClickEliminar ERROR: Formato de audio no soportado para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                System.err.println("ClickEliminar ERROR: Línea de audio no disponible para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) { // Captura IllegalArgumentException aquí
                System.err.println("ClickEliminar ERROR: Error general cargando sonido eliminar '" + soundResourcePath + "'. Mensaje: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void reproducirSonidoEliminar() {
        if (sonidoClip != null) {
            if (sonidoClip.isRunning()) {
                sonidoClip.stop();
            }
            sonidoClip.setFramePosition(0);
            sonidoClip.start();
        } else {
            System.err.println("ClickEliminar: No se puede reproducir el sonido eliminar. El Clip no está cargado o hubo un error en la carga.");
            if (ClickEliminar.class.getResource("/Musica/eliminar.wav") == null) {
                 System.err.println("ClickEliminar INFO: Re-verificación indica que '/Musica/eliminar.wav' sigue sin encontrarse.");
            }
        }
    }

    public static void detenerSonidoEliminar() {
        if (sonidoClip != null && sonidoClip.isRunning()) {
            sonidoClip.stop();
        }
    }

    // Opcional: Método para liberar recursos si es necesario (ej. al cerrar la aplicación)
    public static void close() {
        if (sonidoClip != null) {
            sonidoClip.close();
            System.out.println("MusicaClick: Sonido click (Clip) cerrado.");
        }
    }
    
}
