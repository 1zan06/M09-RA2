public class Forquilla {
    private final int numero;
    private boolean enUs;

    public Forquilla(int numero) {
        this.numero = numero;
        this.enUs = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isEnUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }

    public synchronized boolean agafa() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public synchronized void deixa() {
        enUs = false;
    }
}
