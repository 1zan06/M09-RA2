
import java.util.Random;

public class Assistent extends Thread {
    private final Esdeveniment esdeveniment;
    private final Random rnd = new Random();

    public Assistent(String nom, Esdeveniment esdeveniment) {
        super(nom);
        this.esdeveniment = esdeveniment;
    }

    @Override
    public void run() {
        while (true) { 
            boolean ferReserva = rnd.nextBoolean();

            if (ferReserva) {
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            }

            try {
                Thread.sleep(rnd.nextInt(1001));
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
