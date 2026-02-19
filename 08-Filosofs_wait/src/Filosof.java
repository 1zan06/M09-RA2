import java.util.Random;

public class Filosof extends Thread {
    private final int id;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;

    private int gana = 0;
    private final Random rnd = new Random();

    public Filosof(int id) {
        super("fil" + id);
        this.id = id;
    }

    public void setForquillaEsquerra(Forquilla f) {
        this.forquillaEsquerra = f;
    }

    public void setForquillaDreta(Forquilla f) {
        this.forquillaDreta = f;
    }

    private void pensar() {
        System.out.println("Filosof: " + getName() + " pensant");
        try {
            Thread.sleep(1000 + rnd.nextInt(1001));
        } catch (InterruptedException ignored) {}
    }

    private void menjar() {
        System.out.println("Filosof: " + getName() + " menja");
        try {
            Thread.sleep(1000 + rnd.nextInt(1001));
        } catch (InterruptedException ignored) {}
        System.out.println("Filosof: " + getName() + " ha acabat de menjar");
    }

    private void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar(id);
        System.out.println("Filosof: " + getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
    }

    private void agafarForquillaDreta() {
        forquillaDreta.agafar(id);
        System.out.println("Filosof: " + getName() + " agafa la forquilla dreta " + forquillaDreta.getNumero());
    }

    private void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
    }

    private void agafarForquilles() {
        agafarForquillaEsquerra();
        while (true) { 
            synchronized (forquillaDreta) {
                if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
                    forquillaDreta.agafar(id);
                    System.out.println("Filosof: " + getName() + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                    return;
                }
            }

            forquillaEsquerra.deixar();
            gana++;
            System.out.println("Filosof: " + getName() + " deixa l'esquerra(" + forquillaEsquerra.getNumero() + ") i espera(dreta ocupada)");
            System.out.println("Filosof: " + getName() + " gana=" + gana);

            try {
                Thread.sleep(500 + rnd.nextInt(501));
            } catch (InterruptedException ignored) {}

            agafarForquillaEsquerra();
        }
    }

    @Override
    public void run() {
        while(true) {
            pensar();
            agafarForquilles();
            menjar();
            deixarForquilles();
        }
    }
}
