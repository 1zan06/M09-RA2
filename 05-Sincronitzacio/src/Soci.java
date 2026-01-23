import java.util.Random;

public class Soci extends Thread {
    private final Compte compte;
    private final float aportacio = 10.0f;
    private final int esperaMax = 100;
    private final Random rnd = new Random();
    private final int maxAnys = 10;

    public Soci(String nom) {
        super(nom);
        this.compte = Compte.getInstance();
    }

    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run() {
        int mesosTotals = maxAnys * 12;

        for (int mes = 0; mes < mesosTotals; mes++) {
            if (mes % 2 == 0) {
                compte.ingressa(aportacio);
            } else {
                compte.treu(aportacio);
            }

            try {
                Thread.sleep(rnd.nextInt(esperaMax + 1));
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
