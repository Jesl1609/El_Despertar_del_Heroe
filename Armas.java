
public class Armas {
    private String nombre;
    private String tipo;
    private int dañoMin;
    private int dañoMax;

    public Armas(String nombre, String tipo, int dañoMin, int dañoMax) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dañoMin = dañoMin;
        this.dañoMax = dañoMax;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDañoMin() {
        return dañoMin;
    }

    public void setDañoMin(int dañoMin) {
        this.dañoMin = dañoMin;
    }

    public int getDañoMax() {
        return dañoMax;
    }

    public void setDañoMax(int dañoMax) {
        this.dañoMax = dañoMax;
    }
}
