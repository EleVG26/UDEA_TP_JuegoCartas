import java.util.Random;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 50;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        int i = 0;
        for (Carta c : cartas) {
            cartas[i++] = new Carta(r);
        }

    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();

        int p = 1;
        for (Carta c : cartas) {
            c.mostrar(pnl, MARGEN + TOTAL_CARTAS * DISTANCIA - p++ * DISTANCIA, MARGEN);
        }

        pnl.repaint();

    }

    public int calcularPuntajeRestante() {
        int puntaje = 0;
    
        // Crear un array para contar cuántas cartas hay de cada nombre
        int[] contadores = new int[NombreCarta.values().length];
    
        // Llenar el array con las cartas del jugador
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }
    
        // Sumar el valor de las cartas que no forman parte de grupos
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] < 2) {
                // Asignar valor a las cartas no agrupadas
                if (i >= 9) { // Ace, Jack, Queen, King valen 10
                    puntaje += 10;
                } else {
                    puntaje += (i + 1); // Cartas 2-10 valen su respectivo número
                }
            }
        }
    
        return puntaje;
    }

    // crear un metodo que devuielve un String
    public String getGrupos() {
        String mensaje = "No se encontraron grupos";
        int[] contadores = new int[NombreCarta.values().length];
        // recorrer el total de las cartas
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }
        boolean hayGrupos = false;
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                if (!hayGrupos) {
                    hayGrupos = true;
                    mensaje = "Se encontraron grupos de: \n";
                }
                mensaje += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
            }

        }
        return mensaje;

    }

    // Método para obtener grupos en escalera de la misma pinta
    public String getEscaleras() {
        StringBuilder mensaje = new StringBuilder();
        boolean hayEscalera = false;

        // Crear 4 arrays para las 4 pintas diferentes (13 posibles valores por pinta)
        int[][] cartasPorPinta = new int[4][13];

        // Llenar las cartas por pinta
        for (Carta c : cartas) {
            Pinta pinta = c.getPinta();
            NombreCarta nombre = c.getNombre();
            cartasPorPinta[pinta.ordinal()][nombre.ordinal()]++;
        }

        // Verificar si hay secuencias de 3 o más cartas consecutivas en la misma pinta
        for (int i = 0; i < cartasPorPinta.length; i++) {
            int consecutivas = 0;
            for (int j = 0; j < cartasPorPinta[i].length; j++) {
                if (cartasPorPinta[i][j] > 0) {
                    consecutivas++;
                    if (consecutivas >= 3) {
                        hayEscalera = true;
                        mensaje.append("Escalera de ")
                                .append(Pinta.values()[i])
                                .append(" desde ")
                                .append(NombreCarta.values()[j - consecutivas + 1])
                                .append(" a ")
                                .append(NombreCarta.values()[j])
                                .append("\n");
                    }
                } else {
                    consecutivas = 0; // Reiniciar contador si no hay cartas consecutivas
                }
            }
        }

        if (!hayEscalera) {
            return "No se encontraron escaleras";
        }

        return mensaje.toString();
    }

}
