import java.util.Random;

public class Treballador extends Thread{
    private final float souAnualBrut;
    private final int edatIniciTreball;
    private final int edatFiTreball;

    private int edatActual;
    private float cobrat;

    private final Random rnd;

    public Treballador (String nom, float souAnualBrut, int edatIniciTreball, int edatFiTreball) {
        super(nom);
        this.souAnualBrut = souAnualBrut;
        this.edatIniciTreball = edatIniciTreball;
        this.edatFiTreball = edatFiTreball;

        this.edatActual = 0;
        this.cobrat = 0.0f;

        this.rnd = new Random();
    }

    public void cobra() {
        cobrat += (souAnualBrut / 12.0f);
    }

    public void pagaImpostos() {
        float cobratAquestMes = (souAnualBrut / 12.0f);
        cobrat -= cobratAquestMes * 0.24f;
    }

    @Override
    public void run() {
        while (edatActual < edatFiTreball) {
            if (edatActual >= edatIniciTreball) {
                for (int mes = 0; mes < 12; mes++) {
                    cobra();
                    pagaImpostos();
                }
            }
            edatActual++;
        }
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edatActual;
    }
}
