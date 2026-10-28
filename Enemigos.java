public class Enemigos {
    private String Nombre;
    private int Vida;
    private int DañoMin;
    private int DañoMax;

    public Enemigos(String Nombre, int Vida, int DañoMin, int DañoMax) {
        this.Nombre = Nombre;
        this.Vida = Vida;
        this.DañoMin = DañoMin;
        this.DañoMax = DañoMax;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getVida() {
        return Vida;
    }

    public void setVida(int vida) {
        Vida = vida;
    }

    public int getDañoMin() {
        return DañoMin;
    }

    public void setDañoMin(int dañoMin) {
        DañoMin = dañoMin;
    }

    public int getDañoMax() {
        return DañoMax;
    }

    public void setDañoMax(int dañoMax) {
        DañoMax = dañoMax;
    }
    
}
