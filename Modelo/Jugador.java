
package Modelo;
public class Jugador {
    
    private int id;
    private String nombre;
    private int puntaje;

    public Jugador(int id, String nombre, int puntaje) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public Jugador(String string, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters y setters
    public int getId() { 
        return id; 
    }
    public String getNombre() {
       nombre = nombre.toUpperCase(); 
        return nombre; 
    }
    public int getPuntaje() { 
        return puntaje;
    }
    
}
