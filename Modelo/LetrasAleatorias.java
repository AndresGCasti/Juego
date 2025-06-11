/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LetrasAleatorias {

private static final Map<Character, Double> PROBABILIDADES = new HashMap<>();

    static {
        PROBABILIDADES.put('A', 12.53);
        PROBABILIDADES.put('B', 1.42);
        PROBABILIDADES.put('C', 4.68);
        PROBABILIDADES.put('D', 5.86);
        PROBABILIDADES.put('E', 13.68);
        PROBABILIDADES.put('F', 0.69);
        PROBABILIDADES.put('G', 1.01);
        PROBABILIDADES.put('H', 0.70);
        PROBABILIDADES.put('I', 6.25);
        PROBABILIDADES.put('J', 0.44);
        PROBABILIDADES.put('K', 0.01);
        PROBABILIDADES.put('L', 8.37);
        PROBABILIDADES.put('M', 3.15);
        PROBABILIDADES.put('N', 7.01);
        PROBABILIDADES.put('Ñ', 0.31);
        PROBABILIDADES.put('O', 8.68);
        PROBABILIDADES.put('P', 2.51);
        PROBABILIDADES.put('Q', 0.88);
        PROBABILIDADES.put('R', 6.87);
        PROBABILIDADES.put('S', 7.88);
        PROBABILIDADES.put('T', 4.63);
        PROBABILIDADES.put('U', 3.93);
        PROBABILIDADES.put('V', 0.90);
        PROBABILIDADES.put('W', 0.01);
        PROBABILIDADES.put('X', 0.22);
        PROBABILIDADES.put('Y', 0.90);
        PROBABILIDADES.put('Z', 0.52);
    }

    public char LetrasBoron() {
        double total = PROBABILIDADES.values().stream().mapToDouble(Double::doubleValue).sum();
        double random = Math.random() * total;

        double acumulado = 0.0;
        for (Map.Entry<Character, Double> entry : PROBABILIDADES.entrySet()) {
            acumulado += entry.getValue();
            if (random <= acumulado) {
                return entry.getKey();
            }
        }
        return 'A'; // Caso de seguridad
    }

    /*public static void main(String[] args) {
        // Generar 20 letras de prueba
        for (int i = 0; i < 20; i++) {
            System.out.print(generarLetra() + " ");
        }
    }*/

}
