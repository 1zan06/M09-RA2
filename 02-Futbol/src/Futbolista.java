public class Futbolista extends Thread{

    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private static int ngols = 0;
    private static int ntirades = 0;

    private int golsIndividuals = 0;

    public Futbolista(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            synchronized (Futbolista.class) {
                ntirades++;
                
                if (Math.random() < PROBABILITAT) {
                    ngols++;
                    golsIndividuals++;
                }
            }
        }        
    }

    public int getGolsIndividuals() {
        return golsIndividuals;
    }

    public static void main(String[] args) {
        System.out.println("Inici dels xuts -----------------------");
        
        Futbolista[] futbolistas = new Futbolista[NUM_JUGADORS];

        String[] nombres = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli",
            "Arnau", "Aspas,", "Messi", "Mbappe"};

        //Crear los 11 futbolistas
        for (int i = 0; i < NUM_JUGADORS; i++) {
            futbolistas[i] = new Futbolista(nombres[i]);
        }

        //iniciar els fils
        for (int i = 0; i< NUM_JUGADORS; i++) {
            futbolistas[i].start();
        }

        //esperar a que tots acabin
        for (int i = 0; i< NUM_JUGADORS; i++) {
            try {
                futbolistas[i].join();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("Fi dels xuts ---------------");
        System.out.println("-------- Estadístiques ----------");

        for (int i = 0; i < NUM_JUGADORS; i++) {
            System.out.println(futbolistas[i].getName() + "\t-> " + 
                               futbolistas[i].getGolsIndividuals() + " gols");
        }
    }
}