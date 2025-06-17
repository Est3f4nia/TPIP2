
public class Jugador {
  
    private String nombre; 
    private String posicion; 
    private boolean amonestado; 
    private boolean expulsado;

    // Constructor
    public Jugador(String nombre, String posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.amonestado = false; //  no amonestado
        this.expulsado = false;  //  no expulsado
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public boolean isAmonestado() {
        return amonestado;
    }

    public boolean isExpulsado() {
        return expulsado;
    }

    // Setters (para modificar el estado de amonestación/expulsión)
    public void setAmonestado(boolean amonestado) {
        this.amonestado = amonestado;
    }

    public void setExpulsado(boolean expulsado) {
        this.expulsado = expulsado;
    }

    // Método toString para facilitar la visualización del jugador
    @Override
    public String toString() {
        String estado = "";
        if (amonestado) {
            estado += " (Amonestado)";
        }
        if (expulsado) {
            estado += " (Expulsado)";
        }
        return "Jugador [Nombre: " + nombre + ", Posición: " + posicion + estado + "]";
    }
}
