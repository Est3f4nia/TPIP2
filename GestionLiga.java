import java.util.*;

public class GestionLiga {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Torneo torneo = new Torneo();

        System.out.println("=== GESTIÓN DE LIGA DE FÚTBOL ===");

        // Ingreso de equipos
        System.out.print("¿Cuántos equipos desea ingresar? ");
        int cantEquipos = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < cantEquipos; i++) {
            System.out.print("Ingrese el nombre del equipo #" + (i + 1) + ": ");
            String nombreEquipo = sc.nextLine();
            Equipo nuevoEquipo = new Equipo(nombreEquipo); // En vez de agg a lista de equipos agg en base de datos
            torneo.agregarEquipo(nuevoEquipo); //
        }

        // Mostrar equipos cargados
        System.out.println("\nEquipos cargados:");
        for (Equipo e : torneo.getEquiposOrdenadosPorPuntos()) {
            System.out.println("- " + e.getNombre());
        }

        // Ingreso de partidos
        System.out.println("\nIngrese los partidos. Escriba 'fin' para terminar.");

        while (true) {
            System.out.print("\nEquipo local (o 'fin' para terminar): ");
            String localNombre = sc.nextLine();
            if (localNombre.equalsIgnoreCase("fin")) break;

            System.out.print("Equipo visitante: ");
            String visitanteNombre = sc.nextLine();

            System.out.print("Goles del equipo local: ");
            int golesLocal = Integer.parseInt(sc.nextLine());

            System.out.print("Goles del equipo visitante: ");
            int golesVisitante = Integer.parseInt(sc.nextLine());

            Equipo local = buscarEquipoPorNombre(torneo, localNombre);
            Equipo visitante = buscarEquipoPorNombre(torneo, visitanteNombre);
            
            //Hacer en base de datos
            if (local != null && visitante != null && !local.equals(visitante)) {
                Partido partido = new Partido(local, visitante, golesLocal, golesVisitante);
                torneo.agregarPartido(partido);
            } else {
                System.out.println("Error: uno o ambos equipos no existen, o son el mismo. Intente nuevamente.");
            }
        }

        // Calcular tabla y mostrar resultados
        torneo.calcularPuntos();

        System.out.println("\n=== TABLA DE POSICIONES ===");
        for (Equipo e : torneo.getEquiposOrdenadosPorPuntos()) {
            System.out.println(e);
        }

        Equipo campeon = torneo.definirCampeon();
        System.out.println("\nCAMPEÓN: " + (campeon != null ? campeon.getNombre() : "Empate entre equipos"));
    }

    // Método auxiliar para buscar un equipo por nombre
    private static Equipo buscarEquipoPorNombre(Torneo torneo, String nombre) {
        for (Equipo e : torneo.getEquiposOrdenadosPorPuntos()) {
            if (e.getNombre().equalsIgnoreCase(nombre.trim())) {
                return e;
            }
        }
        return null;
    }
}
