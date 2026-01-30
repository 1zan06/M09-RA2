import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private final int placesMaximes;
    private int placesDisponibles;
    private final List<Assistent> assistents;

    public Esdeveniment(int placesMaximes) {
        this.placesMaximes = placesMaximes;
        this.placesDisponibles = placesDisponibles;
        this.assistents = new ArrayList<>();
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public synchronized void ferReserva(Assistent a) {
        if (assistents.contains(a)) {
            System.out.println(a.getName() + " ja tenia reserva. Places disponibles:" + placesDisponibles);
            return;
        }

        while (placesDisponibles == 0) {
            System.out.println(a.getName() + " espera: no hi ha places. Places disponibles: " + placesDisponibles);
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }

        assistents.add(a);
        placesDisponibles--;
        System.out.println(a.getName() + " ha fer una reserva. Places disponibles: " + placesDisponibles);
    }

    public synchronized void cancelaReserva(Assistent a) {
        if (assistents.remove(a)) {
            placesDisponibles++;
            System.out.println(a.getName() + " ha cancel·lat una reserva. Placeios " + placesDisponibles);
            notifyAll();
        } else {
            System.out.println(a.getName() + " no ha pogut cancel·lar la reserva");
        }
    }
}
