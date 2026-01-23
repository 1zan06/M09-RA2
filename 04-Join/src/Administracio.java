
import java.text.NumberFormat;
import java.util.Locale;

public class Administracio {
    private static final int NUM_POBLACIO_ACTIVA = 50;
    private final Treballador[] poblacioActiva;

    public Administracio() {
        poblacioActiva = new Treballador[NUM_POBLACIO_ACTIVA];

        float sou = 25000.0f;
        int edatInici = 20;
        int edatFi = 65;

        for (int i=0; i<NUM_POBLACIO_ACTIVA; i++) {
            String nom = "Ciutadà-" + i;
            poblacioActiva[i] = new Treballador(nom, sou, edatInici, edatFi);
        }
    }

    public void executaSimulacio() {
        for (Treballador t : poblacioActiva) {
            t.start();
        }

        for (Treballador t : poblacioActiva) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("El main ha sigut interromput mentre esperava: " + t.getName());
            }
        }

        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es", "ES"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        for (Treballador t : poblacioActiva) {
            System.out.println(
                t.getName() + " -> edat: " + t.getEdat() + " / total: " + nf.format(t.getCobrat())
            );
        }
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        admin.executaSimulacio();
    }
}
