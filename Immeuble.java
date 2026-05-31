import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Etage {

    private final int numero;
    private final Queue<Personne> fileAttente = new LinkedList<>();

    public Etage(int numero) {
        this.numero = numero;
    }

    public synchronized void ajouterPersonne(Personne p) {
        fileAttente.add(p);
        notifyAll();
    }

    public synchronized List<Personne> embarquer(boolean ascenseurMonte, int placesDisponibles) {
        List<Personne> personnesEmbarquees = new ArrayList<>();
        Iterator<Personne> it = fileAttente.iterator();

        while (it.hasNext() && personnesEmbarquees.size() < placesDisponibles) {
            Personne p = it.next();
            boolean memeDirection = ascenseurMonte
                    ? p.getDestination() > numero
                    : p.getDestination() < numero;

            if (memeDirection) {
                personnesEmbarquees.add(p);
                it.remove();
            }
        }

        return personnesEmbarquees;
    }

    public synchronized List<Personne> getFileAttenteSnapshot() {
        return new ArrayList<>(fileAttente);
    }

    public int getNumero() {
        return numero;
    }
}
