package TUP.tpi.modelo;

public class Equipo {
    private String nombre;
    private int puntos;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }
}
