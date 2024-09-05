import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase FrmJuego representa la interfaz gráfica del juego de cartas.
 * Contiene dos jugadores, cada uno con su propio panel en una pestaña.
 * Permite repartir las cartas y verificar los grupos y escaleras.
 */

public class FrmJuego extends JFrame {
    // Botones para repartir y verificar cartas
    private JButton btnRepartir;
    private JButton btnVerificar;
    // Paneles donde se muestran las cartas de los jugadores
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
     // Pestañas para cambiar entre jugadores
    private JTabbedPane tpJugadores;

    Jugador jugador1, jugador2;

    /**
     * Constructor que inicializa la interfaz gráfica del juego.
     * Configura los botones, los paneles y los jugadores.
     */
    public FrmJuego() {
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        // Configuración de la ventana principal
        setSize(600, 300);
        setTitle("JUEGO DE CARTAS");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         // Configuración del panel del primer jugador
        pnlJugador1.setBackground(new Color(93, 29, 194));
        pnlJugador1.setLayout(null);
        // Configuración del panel del segundo jugador
        pnlJugador2.setBackground(new Color(41, 154, 194));
        pnlJugador2.setLayout(null);

        // las pestañas donde van los jugadores
        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Esteban", pnlJugador1);
        tpJugadores.addTab("Elena", pnlJugador2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });
        
        // Configuración del botón "Verificar"
        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });
        
        // Agregar los componentes a la ventana
        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);

        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }

    private void btnRepartirClick(ActionEvent evt) {

        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void btnVerificarClick(ActionEvent evt) {
        //CÓDIGO REALIZADO EN CLASE
        // que se muestren los grupos encontrados segun el jugador
        // switch (tpJugadores.getSelectedIndex()) {
        // case 0:
        // JOptionPane.showMessageDialog(null, jugador1.getGrupos());
        // break;

        // case 1:
        // JOptionPane.showMessageDialog(null, jugador2.getGrupos());
        // break;
        // }

        // Determinar qué jugador es el actual según la pestaña seleccionada en el TabbedPane
        Jugador jugadorActual;
        if (tpJugadores.getSelectedIndex() == 0) {
            jugadorActual = jugador1;
        } else {
            jugadorActual = jugador2;
        }
        // Obtener los grupos (pares, ternas, etc.) del jugador actual
        String grupos = jugadorActual.getGrupos();
        String escaleras = jugadorActual.getEscaleras();
        // Calcular el puntaje restante sumando las cartas que no forman parte de ningún grupo
        int puntajeRestante = jugadorActual.calcularPuntajeRestante(jugadorActual.obtenerCartasNoAgrupadas());
        // Mostrar en un mensaje emergente (JOptionPane) los grupos, las escaleras y el puntaje restante del jugador actual
        JOptionPane.showMessageDialog(null, grupos + "\n" + escaleras + "\nPuntaje restante: " + puntajeRestante);
    }
}
