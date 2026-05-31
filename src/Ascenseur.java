import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Ascenseur extends Thread {

    private static final int CAPACITE = 4;

    private int etageCourant = 0;
    private boolean monte = true;
    private final Immeuble immeuble;
    private final List<Personne> cabine = new ArrayList<>();
    private final Semaphore places = new Semaphore(CAPACITE, true);

    public Ascenseur(Immeuble immeuble) {
        this.immeuble = immeuble;
    }

    public synchronized int getEtageCourant() {
        return etageCourant;
    }

    public synchronized boolean isMonte() {
        return monte;
    }

    public synchronized List<Personne> getCabineSnapshot() {
        return new ArrayList<>(cabine);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            descendrePassagers();
            embarquerPassagers();
            pause(1000);
            deplacer();
        }
    }

    private synchronized void descendrePassagers() {
        Iterator<Personne> it = cabine.iterator();

        while (it.hasNext()) {
            Personne p = it.next();

            if (p.getDestination() == etageCourant) {
                it.remove();
                places.release();
                System.out.println(p + " descend etage " + etageCourant);
            }
        }
    }

    private void embarquerPassagers() {
        int etage;
        boolean direction;

        synchronized (this) {
            etage = etageCourant;
            direction = monte;
        }

        List<Personne> personnes = immeuble
                .getEtage(etage)
                .embarquer(direction, places.availablePermits());

        for (Personne p : personnes) {
            try {
                places.acquire();

                synchronized (this) {
                    cabine.add(p);
                }

                System.out.println(p + " monte");
            } catch (InterruptedException e) {
                interrupt();
                return;
            }
        }
    }

    private synchronized void deplacer() {
        if (monte) {
            etageCourant++;
        } else {
            etageCourant--;
        }

        if (etageCourant >= Immeuble.NOMBRE_ETAGES - 1) {
            etageCourant = Immeuble.NOMBRE_ETAGES - 1;
            monte = false;
        }

        if (etageCourant <= 0) {
            etageCourant = 0;
            monte = true;
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
