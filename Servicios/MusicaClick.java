package Servicios;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

//Clase para sonido click del tablero 6x6 del juego.
public class MusicaClick {

    private static Clip sonidoClip;

    static {
        String soundResourcePath = "/Musica/beep.wav";
        URL url = MusicaClick.class.getResource(soundResourcePath);

        System.out.println("MusicaClick: Intentando cargar el recurso de sonido: " + soundResourcePath);
        System.out.println("MusicaClick: URL del recurso resuelta a: " + url);

        if (url == null) {
            System.err.println("MusicaClick ERROR: No se encontró el archivo de sonido '" + soundResourcePath + "' en el paquete 'Servicios'.");
            System.err.println("MusicaClick INFO: Asegúrese de que 'click.wav' esté en la misma carpeta que MusicaClick.class");
            System.err.println("(generalmente la carpeta 'Servicios' dentro de su directorio de salida, ej: bin/Servicios/ o target/classes/Servicios/).");
        } else {
            try {
                // Usar try-with-resources para AudioInputStream si es posible, o cerrar manualmente
                InputStream is = url.openStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);

                sonidoClip = AudioSystem.getClip();
                sonidoClip.open(audioInputStream);
                System.out.println("MusicaClick: Sonido click (Clip) cargado correctamente desde: " + url.toExternalForm());
                
                // Cerrar streams después de abrir el clip si no se usa try-with-resources para AudioInputStream
                // audioInputStream.close(); // Cerrado por clip.open() implícitamente o gestionado de otra forma por el sistema
                // bis.close();
                // is.close();


            } catch (UnsupportedAudioFileException e) {
                System.err.println("MusicaClick ERROR: Formato de audio no soportado para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                System.err.println("MusicaClick ERROR: Línea de audio no disponible para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("MusicaClick ERROR: Error general cargando sonido click '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void reproducirSonidoClick() {
        if (sonidoClip != null) {
            if (sonidoClip.isRunning()) {
                sonidoClip.stop(); // Detiene el sonido si ya se está reproduciendo
            }
            sonidoClip.setFramePosition(0); // Rebobina al inicio
            sonidoClip.start(); // Reproduce el sonido
        } else {
            System.err.println("MusicaClick: No se puede reproducir el sonido click. El Clip no está cargado o hubo un error en la carga.");
            // Puedes añadir una verificación adicional aquí si el URL fue nulo originalmente
            if (MusicaClick.class.getResource("/Musica/beep.wav") == null) {
                 System.err.println("MusicaClick INFO: Re-verificación indica que 'click.wav' sigue sin encontrarse en el paquete Servicios.");
            }
        }
    }

    public static void detenerSonidoClick() {
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


/*package Servicios;

import javafx.scene.media.AudioClip;

public class MusicaClick {

    private static AudioClip sonidoClick;

    static {
        System.out.println("Ruta del sonido: " + MusicaClick.class.getResource("click.wav"));
        try {
            sonidoClick = new AudioClip(MusicaClick.class.getResource("click.wav").toExternalForm());
            sonidoClick.setVolume(1.0);
            System.out.println("Sonido click cargado correctamente.");
        } catch (Exception e) {
            System.err.println("Error cargando sonido click: " + e.getMessage());
        }
    }

    public static void reproducirSonidoClick() {
        if (sonidoClick != null) {
            sonidoClick.play();
        }
    }

    public static void detenerSonidoClick() {
        if (sonidoClick != null) {
            sonidoClick.stop();
        }
    }
}*/


