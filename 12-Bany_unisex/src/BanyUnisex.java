
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    private int estatActual;
    private int ocupants;

    private static final int CAPACITAT_MAX = 3;

    private final Semaphore capacitat;
    private final ReentrantLock lockEstat;

    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }

    public void entraHome(String nom) {
        boolean haEntrat = false;

        while(!haEntrat) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        estatActual = BANY_AMB_HOMES;
                        ocupants++;
                        System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
                        haEntrat = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }

            if (!haEntrat) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public void entraDona(String nom) {
        boolean haEntrat = false;

        while (!haEntrat) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        estatActual = BANY_AMB_DONES;
                        ocupants++;
                        System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
                        haEntrat = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }

            if (!haEntrat) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public void surtHome(String nom) {
        lockEstat.lock();
        try { 
            ocupants--;
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);
            capacitat.release();

            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtDona(String nom) {
        lockEstat.lock();
        try { 
            ocupants--;
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);
            capacitat.release();

            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();

        Home[] homes = new Home[5];
        Dona[] dones = new Dona[5];

        for (int i = 0; i < 5; i++) {
            homes[i] = new Home("Home-" + i, bany);
            dones[i] = new Dona("Dona-" + i, bany);
        }

        for (int i = 0; i < 5; i++) {
            homes[i].start();
            dones[i].start();
        }
    }
}
