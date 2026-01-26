public class Associacio {
    private final int numSocis = 1000;
    private final Soci[] socis;

    public Associacio() {
        socis = new Soci[numSocis];
        for (int i = 0; i< numSocis; i++) {
            socis[i] = new Soci("Soci-" + i);
        }
    }

    public void iniciaCompteTempsSoci() {
        for (Soci s : socis) {
            s.start();
        }
    }

    public void esperaPeriodeSocis() {
        for (Soci s : socis) {
            try {
                s.join();
            } catch (InterruptedException e) {
                System.out.println("Main interromput esperant: " + s.getName());
            }
        }
    }

    public void mostraBalancComptes() {
        float saldoFinal = Compte.getInstance().getSaldo();
        System.out.println("Saldo final del compte: " + saldoFinal);
    }

    public static void main(String[] args) {
        Associacio a = new Associacio();
        a.iniciaCompteTempsSoci();
        a.esperaPeriodeSocis();
        a.mostraBalancComptes();    
    }
}