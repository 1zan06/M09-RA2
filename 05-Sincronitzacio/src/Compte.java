public class Compte {
    private static Compte instancia;
    private float saldo;

    private Compte() {
        this.saldo = 0.0f;
    }

    public static Compte getInstance() {
        if (instancia == null) {
            instancia = new Compte();
        }
        return instancia;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public synchronized void ingressa(float quantitat) {
        saldo += quantitat;
    }

    public synchronized void treu(float quantitat) {
        saldo -= quantitat;
    }
}
