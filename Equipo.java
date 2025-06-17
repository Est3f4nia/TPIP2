
public class Equipo {
    private String nombre;
    private int puntos;
    private int diferenciaGoles;
    private String jugadores;

    public Equipo(String nombre, int puntos, int diferenciaGoles, String jugadores) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.diferenciaGoles = diferenciaGoles;
        this.jugadores = jugadores;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public void setJugadores(String jugadores) {
        this.jugadores = jugadores;
    }
    
}
