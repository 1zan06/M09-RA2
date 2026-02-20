import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    
    private long iniciGana;
    private long fiGana;   
    private int gana;

    private final Random rnd = new Random();
    
    
    public Filosof(String nom) {
        super(nom);
        resetGana();
    }

    public Filosof(int numComensal) {
        super("" + numComensal);
        this.gana = 0;
        this.rnd = new Random();
        this.iniciGana = System.currentTimeMillis();
    }

    public void agafarForquilles() {
        if (agafarForquillaEsquerra()) {
            if (agafarForquillaDreta()) {
                System.out.printf("Fil%s té forquilles esq(%d) dreta(%d)\n", getName(), forquillaEsquerra.getNum(), forquillaDreta.getNum());
                return;
            } else {
                deixarForquilles();
                espera(500, 1001);
            }
        } else {
            espera(500, 1001);
        }
    }

    public void menjar() {
        agafarForquilles();
        System.out.printf("Fil%s menja amb gana &d\n", getName(), calcularGana());
        espera(1000, 2001);
        System.out.printf("Fil%s ha acabat de menjar\n", getName());
        resetGana();
        deixarForquilles();
        System.out.printf("Fil%s deixa les forquilles\n", getName());
    }

    public boolean agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
        return forquillaEsquerra.getBloqueig().isHeldByCurrentThread();
    }

    public boolean agafarForquillaDreta() {
        forquillaDreta.agafar();
        return forquillaDreta.getBloqueig().isHeldByCurrentThread();
    }

    public void deixarForquilles() {
        forquillaEsquerra.deixar();        
        forquillaDreta.deixar();
    }

    public void pensar() {
        resetGana();
        System.out.printf("Fil%s pensant\n", getName());
        espera(1000, 2001);
    }

    public void espera(int num, int limit) {
        try {
            sleep(rnd.nextInt(num, limit));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void calcularGana() {
        this.fiGana = System.currentTimeMillis();
        return (int) ((this.fiGana - this.iniciGana) / 1000);
    }

    public void resetGana() {
        this.iniciGana = System.currentTimeMillis();
        this.gana = 0;
    }

    @Override
    punlic void run() {
        while (true) { 
            menjar();
            pensar();
        }
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;   
    }

    public setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;   
    }

    public setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }
}
