public class Profeta extends Clase {

    private Armas sello;

    public Profeta(String nombre, int Mana, int DañoMax, int DañoMin, int vida) {
        super(nombre, Mana, DañoMax, DañoMin, vida);
        this.sello = new Armas("Sello sagrado", "Fe", 10, 16, 5);
    }

    public Armas getSello() {
        return sello;
    }
}