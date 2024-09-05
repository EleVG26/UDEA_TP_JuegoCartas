/**
 * Clase principal que inicia la aplicación.
 * En el método main, se crea una instancia de FrmJuego,
 * que representa la interfaz gráfica del juego, y se hace visible.
 */

public class App {
    public static void main(String[] args) throws Exception {
        // Crear y mostrar la interfaz gráfica del juego
        new FrmJuego().setVisible(true);
    }
}
