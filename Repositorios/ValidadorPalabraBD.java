package Repositorios;
import Visual.Toast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import javafx.scene.layout.StackPane;

public class ValidadorPalabraBD {
    
    public static Connection conexionActiva;
        
    public int verificarPalabra(String palabra, StackPane root, Set<String> palabrasUsadas) {

        if (palabra == null || palabra.trim().isEmpty()) {
            Toast.mostrar(root, "⚠ Ingresa una palabra.", "advertencia");
            return -1;
        }
        //El trim() elimina los espacios en blanco al inicio y al final de la cadena.
        palabra = palabra.trim().toLowerCase();
        String sql = "SELECT puntaje FROM palabras WHERE palabra = ?";
        
        if (palabrasUsadas.contains(palabra)) {
            Toast.mostrar(root, "🔁 La palabra '" + palabra.toUpperCase() + "' ya fue usada. No vale.", "advertencia");
            return -1;
        }

        try {
            conexionActiva = ConeccionBD.conectar();
            
             if (conexionActiva == null) {
                Toast.mostrar(root, "🚨 No se pudo establecer conexión con la base de datos.", "error");
                return -1;
            }
            PreparedStatement pstmt = conexionActiva.prepareStatement(sql);
            pstmt.setString(1, palabra);
            ResultSet rs = pstmt.executeQuery();

             if (rs.next()) {
                int puntaje = rs.getInt("puntaje");
                palabrasUsadas.add(palabra);
                Toast.mostrar(root, "✅ '" + palabra.toUpperCase() + "' tiene un puntaje de " + puntaje, "exito");
                rs.close();
                pstmt.close();
                return puntaje;
                 } else {
                Toast.mostrar(root, "❌ La palabra no se encuentra ", "error");
                rs.close();
                pstmt.close();
                return 0;
             }
        } catch (Exception e) {
            Toast.mostrar(root, "🚨 Error al consultar la base de datos.", "error");
        }
        return -1;
    }
}
