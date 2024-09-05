import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 * La clase Jugador representa a un jugador en el juego de cartas.
 * Un jugador tiene un conjunto de cartas y puede realizar acciones como repartir, mostrar cartas,
 * verificar si una carta es parte de un grupo o escalera, y calcular su puntaje restante.
 */


public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 50;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    /**
     * Reparte cartas aleatoriamente al jugador.
     * Cada carta se crea utilizando un generador aleatorio.
     */

    public void repartir() {
        int i = 0;
        for (Carta c : cartas) {
            cartas[i++] = new Carta(r);
            // cartas [i++] = new Carta(i); // Se usa para probar la escalera con un set de cartas fijo
        }

    }
    //probar la escalera con cartas fijos
    // public void repartir() {
    // // Aquí nos aseguramos de que las cartas sean de tréboles (índices de 1 a 13)
    // int[] valoresFijos = {6, 7, 8, 9, 10, 11, 12, 13, 1, 2}; // Índices
    // circulares de 6 a 2 en tréboles

    // for (int i = 0; i < TOTAL_CARTAS; i++) {
    // cartas[i] = new Carta(valoresFijos[i]); // Asignar cartas con valores fijos
    // (1 a 13 = tréboles)
    // }
    // }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        // Variable 'p' que actúa como contador para controlar la posición de las cartas
        int p = 1;
        // Itera sobre todas las cartas en la colección "cartas"
        for (Carta c : cartas) {
            c.mostrar(pnl, MARGEN + TOTAL_CARTAS * DISTANCIA - p++ * DISTANCIA, MARGEN);
        }

        pnl.repaint();

    }

    public List<Carta> obtenerCartasNoAgrupadas() {
        List<Carta> cartasNoAgrupadas = new ArrayList<>();

        for (Carta c : cartas) {
            if (!cartaEsParteDeGrupo(c)) { // Este método verifica si la carta está en un grupo
                cartasNoAgrupadas.add(c);
            }
        }

        return cartasNoAgrupadas;
    }
    
    
    private boolean cartaEsParteDeGrupo(Carta carta) {
        // Verificar si la carta está en una escalera
        if (esParteDeEscalera(carta)) {
            return true;
        }
    
        // Verificar si la carta está en un grupo de pares o ternas
        if (esParteDeGrupo(carta)) {
            return true;
        }
    
        // Si no está en ningún grupo ni escalera
        return false;
    }
    
    // Método auxiliar para verificar si la carta está en una escalera
    private boolean esParteDeEscalera(Carta carta) {
        Pinta pinta = carta.getPinta();
        NombreCarta nombre = carta.getNombre();
    
        // Se crea un array temporal que almacene las cartas de la misma pinta
        boolean[] cartasDeMismaPinta = new boolean[13];
    
        // Llenar el array con las cartas de la misma pinta
        for (Carta c : cartas) {
            if (c.getPinta() == pinta) {
                cartasDeMismaPinta[c.getNombre().ordinal()] = true;
            }
        }
    
        // Verificar si la carta es parte de una secuencia de 3 o más cartas consecutivas
        int consecutivas = 0;
        for (int i = 0; i < 13; i++) {
            if (cartasDeMismaPinta[i]) {
                consecutivas++;
                if (consecutivas >= 3 && (i >= nombre.ordinal() && i <= nombre.ordinal() + 2)) {
                    return true; // La carta es parte de una escalera
                }
            } else {
                consecutivas = 0; // Reiniciar si no hay cartas consecutivas
            }
        }
    
        return false;
    }
    
    // Método auxiliar para verificar si la carta está en un grupo de pares o ternas
    private boolean esParteDeGrupo(Carta carta) {
        NombreCarta nombre = carta.getNombre();
        int contador = 0;
    
        // Contar cuántas cartas del mismo valor hay
        for (Carta c : cartas) {
            if (c.getNombre() == nombre) {
                contador++;
            }
        }
    
        // Si hay 2 o más cartas con el mismo valor, es un par o más
        return contador >= 2;
    }
    

    public int calcularPuntajeRestante(List<Carta> cartasNoAgrupadas) {
        int puntaje = 0;
    
        // Sumar el valor de las cartas no agrupadas
        for (Carta c : cartasNoAgrupadas) {
            NombreCarta nombre = c.getNombre();
    
            switch (nombre) {
                case JACK:
                case QUEEN:
                case KING:
                    puntaje += 10; // Figuras (J, Q, K) valen 10 puntos
                    break;
                case AS:
                    puntaje += 1; // Por defecto, As vale 1, o puedes agregar lógica para que valga 11 si lo prefieres
                    break;
                default:
                    // Cartas numéricas (2 al 10)
                    puntaje += nombre.ordinal() + 1; // ordinal() + 1 para obtener el valor real de la carta (2-10)
                    break;
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

        // Verificar si hay secuencias de cartas consecutivas en la misma pinta (con
        // soporte circular)
        for (int i = 0; i < cartasPorPinta.length; i++) {
            int consecutivas = 0;
            int inicioEscalera = -1;
            int maxConsecutivas = 0;
            int maxInicioEscalera = -1;
            int maxFinEscalera = -1;

            // Recorrer el array de cada pinta dos veces para simular un comportamiento
            // circular
            for (int j = 0; j < cartasPorPinta[i].length * 2; j++) {
                int indiceCircular = j % 13; // Usar el índice circular

                if (cartasPorPinta[i][indiceCircular] > 0) {
                    consecutivas++;
                    if (inicioEscalera == -1) {
                        inicioEscalera = indiceCircular; // Marcar el inicio de la escalera
                    }

                    // Si encontramos una nueva secuencia máxima
                    if (consecutivas > maxConsecutivas) {
                        maxConsecutivas = consecutivas;
                        maxInicioEscalera = inicioEscalera;
                        maxFinEscalera = indiceCircular;
                    }
                } else {
                    consecutivas = 0; // Reiniciar contador si no hay cartas consecutivas
                    inicioEscalera = -1; // Reiniciar el inicio de la escalera
                }
            }

            // Si se encontró una secuencia de 3 o más cartas
            if (maxConsecutivas >= 3) {
                hayEscalera = true;
                mensaje.append("Escalera de ")
                        .append(maxConsecutivas)
                        .append(" cartas de ")
                        .append(Pinta.values()[i])
                        .append(" desde ")
                        .append(NombreCarta.values()[maxInicioEscalera])
                        .append(" hasta ")
                        .append(NombreCarta.values()[maxFinEscalera])
                        .append("\n");
            }
        }

        if (!hayEscalera) {
            return "No se encontraron escaleras";
        }

        return mensaje.toString();
    }

}
