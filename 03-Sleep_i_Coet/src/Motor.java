public class Motor extends Thread {
    private int potenciaActual = 0;
    private volatile int potenciaObjectiu = 0;
    boolean rebutOrdre = false;
    private int id;

    public Motor(int id) {
        this.id = id;
    }

    public void setPotencia(int p) {
        potenciaObjectiu = p;
        rebutOrdre = true;
    }

    @Override
    public void run() {
        try {
            while (true) {

                if (potenciaActual < potenciaObjectiu) {
                    potenciaActual++;
                    System.out.println("Motor " + id + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);

                } else if (potenciaActual > potenciaObjectiu) {
                    potenciaActual--;
                    System.out.println("Motor " + id + ": Decre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);

                } else {
                    System.out.println("Motor " + id + ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                }

                if (rebutOrdre && potenciaObjectiu == 0 && potenciaActual == 0) {
                    break;
                }

                int espera = 1000 + (int)(Math.random() * 1000);
                Thread.sleep(espera);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}