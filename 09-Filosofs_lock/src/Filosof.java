import java.util.Random;

public class Filosof extends Thread {

    private Forquilla esquerra;
    private Forquilla dreta;

    private long iniciGana;
    private long fiGana;
    private long gana;
    private final Random rnd = new Random();

    public Filosof(String nom) {
        super(nom);
        resetGana();
    }

    public void setEsquerra(Forquilla f) { this.esquerra = f; }
    public void setDreta(Forquilla f) { this.dreta = f; }

    public void resetGana() {
        iniciGana = 0;
        fiGana = 0;
        gana = 0;
    }

    public long calcularGana() {
        return gana;
    }

    private void pensar() {
        System.out.println(getName() + " pensant");
        try {
            Thread.sleep(1000 + rnd.nextInt(1001)); 
        } catch (InterruptedException ignored) {}
    }

    private void menjar() {
        System.out.println(getName() + " menja amb gana " + calcularGana());
        try {
            Thread.sleep(1000 + rnd.nextInt(1001)); 
        } catch (InterruptedException ignored) {}
        System.out.println(getName() + " ha acabat de menjar");
    }

    private void agafarForquillaEsquerra() {
        esquerra.agafar();
    }

    private void agafarForquillaDreta() {
        dreta.agafar();
    }

    private void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();

        System.out.println(getName() + " té forquilles esq(" + esquerra.getNum() + ") dreta(" + dreta.getNum() + ")");
    }

    private void dixarForquilles() {
        dreta.deixar();
        esquerra.deixar();
        System.out.println(getName() + " deixa les forquilles");
    }

    @Override
    public void run() {
        while (true) {
            pensar();

            iniciGana = System.currentTimeMillis();

            agafarForquilles();

            fiGana = System.currentTimeMillis();
            gana = (fiGana - iniciGana) / 1000;

            menjar();

            dixarForquilles();

            resetGana();
        }
    }
}
