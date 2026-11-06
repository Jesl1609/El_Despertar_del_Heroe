public class Guerrero extends Clase {

    private Armas daga;
    private Armas espadaLarga;
    private Armas espadon;

    public Guerrero(String nombre, int Mana, int DañoMax, int DañoMin, int vida) {
        super(nombre, Mana, DañoMax, DañoMin, vida);
        this.daga = new Armas("Daga", "Ligera", 10, 14, 0);
        this.espadaLarga = new Armas("Espada larga", "Mediana", 12, 17, 20);
        this.espadon = new Armas("Espadón", "Pesada", 15, 20, 40);
    }

    public Armas getDaga() {
        return daga;
    }

    public Armas getEspadaLarga() {
        return espadaLarga;
    }

    public Armas getEspadon() {
        return espadon;
    }
}