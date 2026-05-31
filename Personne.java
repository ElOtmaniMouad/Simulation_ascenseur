public class Immeuble {

    public static final int NOMBRE_ETAGES = 5;

    private final Etage[] etages;

    public Immeuble() {
        etages = new Etage[NOMBRE_ETAGES];

        for (int i = 0; i < NOMBRE_ETAGES; i++) {
            etages[i] = new Etage(i);
        }
    }

    public Etage getEtage(int i) {
        return etages[i];
    }

    public Etage[] getEtages() {
        return etages;
    }
}
