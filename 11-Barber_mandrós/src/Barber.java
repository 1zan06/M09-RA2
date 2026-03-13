
import java.util.Random;

public class Barber extends Thread {
    private final Random rnd;

    public Barber(String nom) {
        super(nom);
        this.rnd = new Random();
    }

    @Override
    public void run() {
        Barberia barberia = Barberia.getInstancia();

        while (true) { 
            Client client = barberia.seguentClient();

            if (client == null) {
                System.out.println("Ningú en espera");
                System.out.println("Barber " + getName() + " dormint");

                synchronized (barberia.getCondBarber()) {
                    try {
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            } else {
                System.out.println("Li toca al client " + client.getNom());
                client.tallarseElCabell();

                try {
                    Thread.sleep(900 + rnd.nextInt(101));
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
