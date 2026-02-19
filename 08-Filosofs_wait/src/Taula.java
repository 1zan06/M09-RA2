public class Taula {
    private final int numFilosofs;
    private final Filosof[] comensals;
    private final Forquilla[] forquilles;

    public Taula(int numFilosofs) {
        this.numFilosofs = numFilosofs;
        this.comensals = new Filosof[numFilosofs];
        this.forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            comensals[i] = new Filosof(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            comensals[i].setForquillaEsquerra(forquilles[i]);
            comensals[i].setForquillaDreta(forquilles[(i + 1) % numFilosofs]);
        }
    }

    public void showTaula() {
        for (int i = 0; i < numFilosofs; i++) {
            int esq = forquilles[i].getNumero();
            int dret = forquilles[(i + 1) % numFilosofs].getNumero();
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
        Taula t = new Taula(5);
        t.showTaula();
        t.cridarATaula();
    }
}
