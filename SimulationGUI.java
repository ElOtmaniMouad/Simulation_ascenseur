public class Main {

    public static void main(String[] args) {
        Immeuble immeuble = new Immeuble();
        Ascenseur ascenseur = new Ascenseur(immeuble);
        GenerateurPersonnes gen = new GenerateurPersonnes(immeuble);

        ascenseur.start();
        gen.start();

        javax.swing.SwingUtilities.invokeLater(() ->
                new SimulationGUI(immeuble, ascenseur));
    }
}
