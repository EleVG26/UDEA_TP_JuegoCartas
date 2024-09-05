import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmJuego extends JFrame {
    private JButton btnRepartir;
    private JButton btnVerificar;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JTabbedPane tpJugadores;

    Jugador jugador1, jugador2;

    public FrmJuego() {
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        setSize(600, 300);
        setTitle("JUEGO DE CARTAS");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pnlJugador1.setBackground(new Color(93, 29, 194));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(41, 154, 194));
        pnlJugador2.setLayout(null);

        // las pestañas
        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín", pnlJugador1);
        tpJugadores.addTab("Raul", pnlJugador2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

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

        // pnlJugador1.removeAll();
        // Random r= new Random();
        // Carta c = new Carta(r);
        // c.mostrar(pnlJugador1, 10, 10);

        // pnlJugador1.repaint();

    }

    private void btnVerificarClick(ActionEvent evt) {
        // que se muestren los grupos encontrados segun el jugador
        // switch (tpJugadores.getSelectedIndex()) {
        // case 0:
        // JOptionPane.showMessageDialog(null, jugador1.getGrupos());
        // break;

        // case 1:
        // JOptionPane.showMessageDialog(null, jugador2.getGrupos());
        // break;
        // }
        Jugador jugadorActual;
        if (tpJugadores.getSelectedIndex() == 0) {
            jugadorActual = jugador1;
        } else {
            jugadorActual = jugador2;
        }

        String grupos = jugadorActual.getGrupos();
        String escaleras = jugadorActual.getEscaleras();
        int puntajeRestante = jugadorActual.calcularPuntajeRestante();

        JOptionPane.showMessageDialog(null, grupos + "\n" + escaleras + "\nPuntaje restante: " + puntajeRestante);

    }

}
