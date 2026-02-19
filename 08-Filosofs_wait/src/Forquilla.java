public class Forquilla {

    public static final int LLIURE = -1;

    private final int numero;
    private int propietari;

    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }

    public int getNumero() {
        return numero;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }

    public synchronized void agafar(int idFilosof) {
        while (propietari != LLIURE) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        propietari = idFilosof;
    }

    public synchronized void deixar() {
        propietari = LLIURE;
        notifyAll();
    }
}
