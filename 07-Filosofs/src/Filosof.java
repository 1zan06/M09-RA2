import java.util.Random;

public class Filosof extends Thread {

    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;

    private int gana;
    private final Random rnd = new Random();

    public Filosof(String nom) {
        super(nom);
        this.gana = 0;
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
            Thread.sleep(1000 + rnd.nextInt(1001)); // 1..2s
        } catch (InterruptedException ignored) {}
    }

    private void menjar() {
        System.out.println("Filosof: " + getName() + " menja");
        try {
            Thread.sleep(1000 + rnd.nextInt(1001)); // 1..2s
        } catch (InterruptedException ignored) {}
        System.out.println("Filosof: " + getName() + " ha acabat de menjar");
    }

    @Override
    public void run() {
        while (true) {
            pensar();

            boolean teEsquerra = forquillaEsquerra.agafa();
            if (teEsquerra) {
                System.out.println("Filosof: " + getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());

                boolean teDreta = forquillaDreta.agafa();
                if (teDreta) {
                    System.out.println("Filosof: " + getName() + " agafa la forquilla dreta " + forquillaDreta.getNumero());

                    menjar();

                    forquillaDreta.deixa();
                    forquillaEsquerra.deixa();

                } else {
                    forquillaEsquerra.deixa();
                    gana++;
                    System.out.println("Filosof: " + getName() + " deixa l'esquerra(" + forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                    System.out.println("Filosof: " + getName() + " gana=" + gana);

                    try {
                        Thread.sleep(500 + rnd.nextInt(501)); // 0.5..1s
                    } catch (InterruptedException ignored) {}
                }

            } else {
                try {
                    Thread.sleep(500 + rnd.nextInt(501)); // 0.5..1s
                } catch (InterruptedException ignored) {}
            }
        }
    }
}

