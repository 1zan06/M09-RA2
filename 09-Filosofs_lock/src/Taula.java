public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;

    public Taula() {

    }

    public Taula(int numFilosofs) {
        this.comensals = new Filosof[numFilosofs];
        this.forquilles = new Forquilla[numFilosofs];

        crearForquilles();
        crearFilosofs();
        assignarForquilles();
    }

    public void crearForquilles() {
        for (int i = 0; i < forquilles.length; i++) {
            forquilles[i] = new Forquilla(i);   
        }
    }

    public void crearFilosofs() {
        for (int i = 0; i < comensals.length; i++) {
            comensals[i] = new Filosof(i);
        }
    }

    public void assignarForquilles() {
        int indexForquilla = 0;
        for (int i = 0; i < comensals.length; i++) {
            for (int j = indexForquilla; j < forquilles.length; j++) {
                Forquilla esquerra = forquilles[i];
                Forquilla dreta = forquilles[(i + 1) % forquilles.length];
                comensals[i].setForquillaEsquerra(esquerra);
                comensals[i].setForquillaDreta(dreta);
                if (i < comensals.length) {
                    indexForquilla = j + 1;
                    break;
                }
            }
        }
    }

    public void showTaula() {
        for (Filosof fil : comensals) {
            System.out.printf("\nComensal:fil%s esq:%s dret:%s", fil.getName(), fil.getForquillaDreta().getNum(), fil.getForquillaDreta().getNum());
        }
        System.out.println("\n-----------------------------");
    }

    public void cridaATaula() {
        for (Filosof fil : comensals) {
            fil.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridaATaula();
    }
}
