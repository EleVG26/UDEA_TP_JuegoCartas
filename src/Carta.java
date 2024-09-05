import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carta {

    private int indice;
    //correcciones
    private Pinta pinta;

    public Carta(Random r) {
        indice = r.nextInt(52) + 1;

        //Esto es solamente para verificar la escalera con cartas fijas
        //public Carta(int indice) {
        //this.indice = indice;

        // Determinar la pinta en función del índice
        if (indice <= 13) {
            pinta = Pinta.TREBOL; // Índices de 1 a 13 son tréboles
        } else if (indice <= 26) {
            pinta = Pinta.PICA; // Índices de 14 a 26 son picas
        } else if (indice <= 39) {
            pinta = Pinta.CORAZON; // Índices de 27 a 39 son corazones
        } else {
            pinta = Pinta.DIAMANTE; // Índices de 40 a 52 son diamantes
        }
    }

    // se muestra en un panel
    public void mostrar(JPanel pnl, int x, int y) {
        String nombreImagen = "/imagenes/Carta" + String.valueOf(indice) + ".jpeg";
        ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen));

        JLabel lbl = new JLabel(imagen);
        // coordenadas
        lbl.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());

        pnl.add(lbl); // panel agrega el label
    }

    // obtener pinta, código realizado en clase
    // public Pinta getPinta() {
    //     if (indice <= 13) {
    //         return Pinta.TREBOL;
    //     } else if (indice <= 26) {
    //         return Pinta.PICA;
    //     } else if (indice <= 39) {
    //         return Pinta.CORAZON;
    //     } else {
    //         return Pinta.DIAMANTE;
    //     }

    // }

    
    //El método pinta se pasa al constructor y luego se hace llamado a la función
    public Pinta getPinta() {
        return pinta;
    }


    // obtener nombre
    public NombreCarta getNombre() {
        int residuo = indice % 13;
        if (residuo == 0) {
            residuo = 13;
        }
        return NombreCarta.values()[residuo - 1];

    }

}
