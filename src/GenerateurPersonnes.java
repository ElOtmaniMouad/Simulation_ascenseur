import java.util.Random;

public class GenerateurPersonnes extends Thread {

    private final Immeuble immeuble;
    private int compteur = 1;
    private final Random r = new Random();

    public GenerateurPersonnes(Immeuble immeuble) {
        this.immeuble = immeuble;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            int depart = r.nextInt(Immeuble.NOMBRE_ETAGES);
            int dest;

            do {
                dest = r.nextInt(Immeuble.NOMBRE_ETAGES);
            } while (dest == depart);

            Personne p = new Personne(compteur++, depart, dest);
            immeuble.getEtage(depart).ajouterPersonne(p);

            System.out.println("Nouvelle personne : " + p + " depart etage " + depart);
            pause(2500);
        }
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            interrupt();
        }
    }
}
