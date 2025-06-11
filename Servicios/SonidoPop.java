
package Servicios;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Clase para sonidos secundarios como para el bton de play, comoJugar, atrás, etc...
public class SonidoPop {
    
    private static Clip sonidoClip;

    static {
        // Busca click.wav en el mismo paquete que MusicaClick.class (es decir, en la carpeta Servicios)
        String soundResourcePath = "/Musica/pop.wav";
        URL url = MusicaClick.class.getResource(soundResourcePath); // Adaptado de

        System.out.println("SonidoPop: Intentando cargar el recurso de sonido: " + soundResourcePath);
        System.out.println("SonidoPop: URL del recurso resuelta a: " + url);

        if (url == null) {
            System.err.println("SonidoPop ERROR: No se encontró el archivo de sonido '" + soundResourcePath + "' en el paquete 'Servicios'.");
            System.err.println("SonidoPop INFO: Asegúrese de que '/Musica/pop.wav' esté en la misma carpeta que MusicaClick.class");
            System.err.println("(generalmente la carpeta 'Servicios' dentro de su directorio de salida, ej: bin/Servicios/ o target/classes/Servicios/).");
        } else {
            try {
                // Usar try-with-resources para AudioInputStream si es posible, o cerrar manualmente
                InputStream is = url.openStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);

                sonidoClip = AudioSystem.getClip();
                sonidoClip.open(audioInputStream);
                System.out.println("SonidoPop: Sonido click (Clip) cargado correctamente desde: " + url.toExternalForm());
                
                // Cerrar streams después de abrir el clip si no se usa try-with-resources para AudioInputStream
                // audioInputStream.close(); // Cerrado por clip.open() implícitamente o gestionado de otra forma por el sistema
                // bis.close();
                // is.close();


            } catch (UnsupportedAudioFileException e) {
                System.err.println("SonidoPop ERROR: Formato de audio no soportado para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                System.err.println("SonidoPop ERROR: Línea de audio no disponible para '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("SonidoPop ERROR: Error general cargando sonido click '" + soundResourcePath + "'. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void reproducirSonidoPop() {
        if (sonidoClip != null) {
            if (sonidoClip.isRunning()) {
                sonidoClip.stop(); // Detiene el sonido si ya se está reproduciendo
            }
            sonidoClip.setFramePosition(0); // Rebobina al inicio
            sonidoClip.start(); // Reproduce el sonido
        } else {
            System.err.println("SonidoPop: No se puede reproducir el sonido click. El Clip no está cargado o hubo un error en la carga.");
            // Puedes añadir una verificación adicional aquí si el URL fue nulo originalmente
            if (MusicaClick.class.getResource("/Musica/pop.wav") == null) {
                 System.err.println("SonidoPop INFO: Re-verificación indica que 'click.wav' sigue sin encontrarse en el paquete Servicios.");
            }
        }
    }

    public static void detenerSonidoPop() {
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
