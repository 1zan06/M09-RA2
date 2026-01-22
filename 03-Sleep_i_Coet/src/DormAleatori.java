public class DormAleatori extends Thread{
    private long instantCreacio;

    public DormAleatori(String nom) {
        super(nom); 
        instantCreacio = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try{
                int interval = (int) (Math.random() * 1000);
                long tempsTotal = System.currentTimeMillis() - instantCreacio;

                System.out.println(
                    getName() + " (" + i + ") a dormir " + 
                    interval + "ms total " + tempsTotal + "ms"
                );

                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori d1 = new DormAleatori("Joan");
        DormAleatori d2 = new DormAleatori("Pep");

        d1.start();
        d2.start();

        System.out.println("Fi de main");
    }
}

