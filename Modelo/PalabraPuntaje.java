
package Modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PalabraPuntaje {
    private final SimpleStringProperty palabra;
    private final SimpleIntegerProperty puntaje;
    

    public PalabraPuntaje(String palabra, int puntaje) {
        this.palabra = new SimpleStringProperty(palabra);
        this.puntaje = new SimpleIntegerProperty(puntaje);
    }

    public String getPalabra() {
        return palabra.get();
    }

    public void setPalabra(String palabra) {
        this.palabra.set(palabra);
    }

    public int getPuntaje() {
        return puntaje.get();
    }

    public void setPuntaje(int puntaje) {
        this.puntaje.set(puntaje);
    }
}
