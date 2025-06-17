import java.util.ArrayList; 
import java.util.List;  

public class Torneo {
   
    private List<Equipo> equipos; 
    private List<Partido> partidos;
    private Equipo campeon; 

    // Constructor
    public Torneo() {
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.campeon = null; 
    }

    // Método para agregar equipos 
    public void agregarEquipo(Equipo equipo) {
        if (equipo != null && !equipos.contains(equipo)) {
            this.equipos.add(equipo);
        }
    }

    // Método para agregar partidos 
    public void agregarPartido(Partido partido) {
        if (partido != null) {
            this.partidos.add(partido);
        }
    }

    // Métodos 
    public void calcularPuntos() { 
        for (Equipo equipo : equipos) {
            equipo.setPuntos(0);
            equipo.setDiferenciaGoles(0);
        }

        for (Partido partido : partidos) {
            Equipo ganador = partido.getGanador();
            Equipo local = partido.geteLocal();
            Equipo visitante = partido.geteVisitante();
        }
    }
}
