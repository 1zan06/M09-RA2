public class Client {
    private final String nom;

    public Client(int id) {
        this.nom = "Client-" + id;
    }

    public String getNom() {
        return nom;
    }

    public void tallarseElCabell() {
        System.out.println("Tallant el cabell a " + nom);
    }
}
