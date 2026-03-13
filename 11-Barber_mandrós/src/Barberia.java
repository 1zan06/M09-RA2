import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private final Queue<Client> salaEspera;
    private final int maxCadires;
    private final Object condBarber;

    private static Barberia instancia;

    public Barberia(int maxCadires) {
        this.salaEspera = new LinkedList<Client>();
        this.maxCadires = maxCadires;
        this.condBarber = new Object();
        instancia = this;
    }

    public static Barberia getInstancia() {
        return instancia;
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public synchronized Client seguentClient() {
        if (salaEspera.isEmpty()) {
            return null;
        }
        return salaEspera.poll();
    }

    public synchronized void entrarClient(Client c) {
        if (salaEspera.size() < maxCadires) {
            salaEspera.add(c);
            System.out.println("Client " + c.getNom() + " en espera");

            synchronized (condBarber) {
                condBarber.notifyAll();
            }
        } else {
            System.out.println("No queden cadires, client " + c.getNom() + " se'n va");
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                entrarClient(new Client(i));
                Thread.sleep(500);
            }

            Thread.sleep(10000);

            for (int i = 11; i <= 20; i++) {
                entrarClient(new Client(i));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Barberia interrompuda");
        }
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");

        barber.start();
        barberia.start();
    }
}
