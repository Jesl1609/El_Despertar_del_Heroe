public class Astrologo extends Clase {

    private Armas baston;

    public Astrologo(String nombre, int Mana, int DañoMax, int DañoMin, int vida) {
        super(nombre, Mana, DañoMax, DañoMin, vida);
        this.baston = new Armas("Bastón de astrólogo", "Mágica", 14, 20, 15);
    }

    public Armas getBaston() {
        return baston;
    }
}