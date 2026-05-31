import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.Font;

public class SimulationGUI extends JFrame {

    private final Immeuble immeuble;
    private final Ascenseur ascenseur;
    private final JTextArea zone;

    public SimulationGUI(Immeuble immeuble, Ascenseur ascenseur) {
        this.immeuble = immeuble;
        this.ascenseur = ascenseur;

        setTitle("Simulation Ascenseur");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        zone = new JTextArea();
        zone.setEditable(false);
        zone.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(new JScrollPane(zone));
        setLocationRelativeTo(null);
        setVisible(true);

        Timer timer = new Timer(500, e -> actualiser());
        timer.start();
    }

    private void actualiser() {
        StringBuilder sb = new StringBuilder();

        sb.append("ASCENSEUR : etage ")
                .append(ascenseur.getEtageCourant())
                .append(" ")
                .append(ascenseur.isMonte() ? "montee" : "descente")
                .append("\n");

        sb.append("Cabine : ")
                .append(ascenseur.getCabineSnapshot())
                .append("\n\n");

        for (int i = Immeuble.NOMBRE_ETAGES - 1; i >= 0; i--) {
            sb.append("Etage ")
                    .append(i)
                    .append(" : ")
                    .append(immeuble.getEtage(i).getFileAttenteSnapshot())
                    .append("\n");
        }

        zone.setText(sb.toString());
    }
}
