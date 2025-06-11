package Repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConeccionBD {

    // Ruta del archivo .properties dentro del paquete
    private static final String PROPERTIES_FILE = "/Repositorios/config.properties";

    private static String URL;
    private static String USUARIO;
    private static String CONTRASEÑA;

    // Bloque estático: se ejecuta al cargar la clase
    static {
        try (InputStream input = ConeccionBD.class.getResourceAsStream(PROPERTIES_FILE)) {
            Properties props = new Properties();
            props.load(input);

            URL = props.getProperty("db.url");
            USUARIO = props.getProperty("db.user");
            CONTRASEÑA = props.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("❌ No se pudo cargar el archivo de propiedades: " + e.getMessage());
        }
    }

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }

    public static void desconectar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("✅ Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

