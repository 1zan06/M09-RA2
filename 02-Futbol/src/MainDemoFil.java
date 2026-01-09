
public class MainDemoFil {
    public static void main(String[] args) {
        Thread filActual = Thread.currentThread();

        int prioritat = filActual.getPriority();
        String nom = filActual.getName();
        String info = filActual.toString();

        System.out.println("Prrioritat: " + prioritat + ", Nom: " + nom);
        System.out.println("toString: " + info);
    }
}
