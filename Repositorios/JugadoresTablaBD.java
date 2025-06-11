package Repositorios;

import Modelo.Jugador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadoresTablaBD {

    private Connection conn;

    public JugadoresTablaBD(Connection conn) {
        this.conn = conn;
    }

    public void insertarJugador(String nombre, int puntaje) throws SQLException {
        // 1. Contar jugadores
        String contarSQL = "SELECT COUNT(*) FROM jugadores";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(contarSQL)) {
            if (rs.next()) {
                int cantidad = rs.getInt(1);

                if (cantidad < 10) {
                    // Menos de 10 jugadores, insertar directamente
                    insertar(nombre, puntaje);
                } else {
                    // Hay 10 o más: obtener el menor puntaje
                    String minSQL = "SELECT MIN(puntaje) FROM jugadores";
                    try (Statement minStmt = conn.createStatement(); ResultSet minRS = minStmt.executeQuery(minSQL)) {
                        if (minRS.next()) {
                            int puntajeMinimo = minRS.getInt(1);

                            if (puntaje > puntajeMinimo) {
                                // Eliminar TODOS los de menor puntaje
                                String borrarSQL = "DELETE FROM jugadores WHERE puntaje = ?";
                                try (PreparedStatement borrarStmt = conn.prepareStatement(borrarSQL)) {
                                    borrarStmt.setInt(1, puntajeMinimo);
                                    borrarStmt.executeUpdate();
                                }

                                // Insertar nuevo jugador
                                insertar(nombre, puntaje);
                            } else {
                                // No se inserta porque el puntaje no es mejor que el mínimo
                                System.out.println("Puntaje muy bajo, no se guarda en el top 10.");
                            }
                        }
                    }
                }
            }
        }
    }

    // Método auxiliar para insertar
    private void insertar(String nombre, int puntaje) throws SQLException {
        String insertarSQL = "INSERT INTO jugadores (nombre, puntaje) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertarSQL)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, puntaje);
            pstmt.executeUpdate();
        }
    }

    public List<Jugador> obtenerJugadoresOrdenados() throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String querySQL = "SELECT * FROM jugadores ORDER BY puntaje DESC LIMIT 10";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(querySQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int puntaje = rs.getInt("puntaje");
                jugadores.add(new Jugador(id, nombre, puntaje));
            }
        }
        return jugadores;
    }

    // 🔽 MÉTODO ESTÁTICO AÑADIDO PARA USO DESDE EL CONTROLADOR
    public static List<Jugador> obtenerTopJugadoresDesdeBD() {
        List<Jugador> jugadores = new ArrayList<>();
        Connection conn = ConeccionBD.conectar();
        if (conn != null) {
            try {
                JugadoresTablaBD tabla = new JugadoresTablaBD(conn);
                jugadores = tabla.obtenerJugadoresOrdenados();
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener jugadores", e);
            } finally {
                ConeccionBD.desconectar(conn);
            }
        }
        return jugadores;
    }
}

