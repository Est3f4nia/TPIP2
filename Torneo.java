
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Torneo {

    private List<Equipo> equipos; // Crear en base de datos
    private List<Partido> partidos; // Crear en base de datos
    private Equipo campeon;

    public Torneo() {
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.campeon = null;
    }

    public void agregarEquipo(Equipo equipo) {
        if (equipo != null && !equipos.contains(equipo)) {
            equipos.add(equipo);
        }
    }

    public void agregarPartido(Partido partido) {
        if (partido != null) {
            partidos.add(partido);
        }
    }

    public void calcularPuntos() {
        for (Equipo equipo : equipos) {
            equipo.setPuntos(0);
            equipo.setDiferenciaGoles(0);
        }

        for (Partido partido : partidos) {
            Equipo local = partido.geteLocal();
            Equipo visitante = partido.geteVisitante();
            int golesLocal = partido.getGolesLocal();
            int golesVisitante = partido.getGolesVisitante();

            if (golesLocal > golesVisitante) {
                local.actualizarPuntos(3, golesLocal, golesVisitante);
                visitante.actualizarPuntos(0, golesVisitante, golesLocal);
            } else if (golesVisitante > golesLocal) {
                visitante.actualizarPuntos(3, golesVisitante, golesLocal);
                local.actualizarPuntos(0, golesLocal, golesVisitante);
            } else {
                local.actualizarPuntos(1, golesLocal, golesVisitante);
                visitante.actualizarPuntos(1, golesVisitante, golesLocal);
            }
        }
    }

    public Equipo definirCampeon() {
        if (equipos.isEmpty()) return null;

        equipos.sort(Comparator.comparingInt(Equipo::getPuntos)
                .thenComparingInt(Equipo::getDiferenciaGoles)
                .reversed());

        Equipo primero = equipos.get(0);
        Equipo segundo = equipos.size() > 1 ? equipos.get(1) : null;

        if (segundo != null && primero.getPuntos() == segundo.getPuntos() &&
            primero.getDiferenciaGoles() == segundo.getDiferenciaGoles()) {
            return null; // Empate total
        }

        return primero;
    }

    public List<Equipo> getEquiposOrdenadosPorPuntos() {
        equipos.sort(Comparator.comparingInt(Equipo::getPuntos)
                .thenComparingInt(Equipo::getDiferenciaGoles)
                .reversed());
        return equipos;
    }
}
