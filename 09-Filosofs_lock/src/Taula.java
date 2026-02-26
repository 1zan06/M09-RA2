public class Taula {

    private final int n;
    private final Filosof[] comensals;
    private final Forquilla[] forquilles;

    public Taula(int n) {
        this.n = n;
        this.comensals = new Filosof[n];
        this.forquilles = new Forquilla[n];

        for (int i = 0; i < n; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < n; i++) {
            comensals[i] = new Filosof("Fil" + i);
        }

        for (int i = 0; i < n; i++) {
            comensals[i].setEsquerra(forquilles[i]);
            comensals[i].setDreta(forquilles[(i + 1) % n]);
        }
    }

    public void showTaula() {
        for (int i = 0; i < n; i++) {
            int esq = i;
            int dret = (i + 1) % n;
            System.out.println("Comensal:" + comensals[i].getName() + " esq:" + esq + " dret:" + dret);
        }
        System.out.println("---------------------------");
    }

    public void cridarATaula() {
        for (Filosof f : comensals) {
            f.start();
        }
    }

    public static void main(String[] args) {
        Taula t = new Taula(4);
        t.showTaula();
        t.cridarATaula();
    }
}
