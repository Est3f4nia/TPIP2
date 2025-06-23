package TUP.tpi.modelo;

public class Jugador {
    private int dni;
    private String nombre;
    private String posicion;
    private int idEquipo;

    public Jugador(int dni, String nombre, String posicion, int idEquipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.posicion = posicion;
        this.idEquipo = idEquipo;
    }

    public int getDni() {
        return dni;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }
    
    public int getidEquipo() {
        return idEquipo;
    }

}

