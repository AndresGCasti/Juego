
package Servicios;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

//Clase para la musica general del juego
public class MusicaGlobal {

    private static Clip clip;
    private static boolean pausado = false;
    private static long posicionPausa = 0;

    public static void iniciarMusica() {
        try {
            if (clip == null || !clip.isOpen()) {
                // Cambia la extensión del archivo a ".wav"
                URL url = MusicaGlobal.class.getResource("/Musica/Lights.wav");
                if (url == null) {
                    System.err.println("No se encontró el archivo WAV.");
                    return;
                }

                InputStream is = url.openStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                // Solo necesitas crear el AudioInputStream sin decodificación para WAV
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);

                // Obtiene el clip y lo reproduce
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                // Ejemplo para bajar el volumen a -10.0 dB. Ajusta el valor -10.0f según necesites (valores más
                //negativos = más bajo).
                if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                   ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-15.0f);
                }
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                pausado = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pausarReanudar() {
        if (clip != null) {
            if (clip.isRunning()) {
                posicionPausa = clip.getMicrosecondPosition();
                clip.stop();
                pausado = true;
            } else if (pausado) {
                clip.setMicrosecondPosition(posicionPausa);
                clip.start();
                pausado = false;
            }
        }
    }

    public static boolean estaSonando() {
        return clip != null && clip.isRunning();
    }

    public static void detenerMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
            posicionPausa = 0;
            pausado = false;
        }
    }
}
