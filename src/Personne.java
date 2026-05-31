public class Personne {

    private final int id;
    private final int depart;
    private final int destination;

    public Personne(int id, int depart, int destination) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public int getDepart() {
        return depart;
    }

    public int getDestination() {
        return destination;
    }

    public boolean monte() {
        return destination > depart;
    }

    @Override
    public String toString() {
        return "P" + id + "(" + destination + ")";
    }
}
