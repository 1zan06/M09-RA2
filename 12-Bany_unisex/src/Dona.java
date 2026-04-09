import java.util.Random;

public class Dona extends Thread{
    private final BanyUnisex lavabo;
    private final Random rnd;

    public Dona(String nom, BanyUnisex lavabo) {
        super(nom);
        this.lavabo = lavabo;
        this.rnd = new Random();
    }

    private void utilitzaLavabo() {
        try { 
            Thread.sleep(2000 + rnd.nextInt(1001));
        } catch (InterruptedException ignora) {
        }
    }

    @Override
    public void run() {
        System.out.println(getName() + " vol entrar al bany");
        lavabo.entraDona(getName());
        utilitzaLavabo();
        System.out.println(getName() + " acaba d'usar del bany");
        lavabo.surtDona(getName());
    }
}
