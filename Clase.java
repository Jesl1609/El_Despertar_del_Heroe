public class Clase {
    private String nombre;
    private int Mana;
    private int DañoMax;
    private int DañoMin;
    private int vida;

    public Clase(String nombre, int Mana, int DañoMax, int DañoMin, int vida) {
        this.nombre = nombre;
        this.Mana = Mana;
        this.DañoMax = DañoMax;
        this.DañoMin = DañoMin;
        this.vida = vida;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getMana() { return Mana; }
    public void setMana(int mana) { Mana = mana; }

    public int getDañoMax() { return DañoMax; }
    public void setDañoMax(int dañoMax) { DañoMax = dañoMax; }

    public int getDañoMin() { return DañoMin; }
    public void setDañoMin(int dañoMin) { DañoMin = dañoMin; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
}