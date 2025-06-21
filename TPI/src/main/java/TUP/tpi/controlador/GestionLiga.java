package TUP.tpi.controlador;

import java.util.Scanner;
import TUP.tpi.modelo.Equipo;
import TUP.tpi.modelo.Jugador;
import TUP.tpi.modelo.Partido;
import TUP.tpi.vista.Menu;
import java.sql.*;

public class GestionLiga {
    public static void cargaEquipos(){
        Scanner sc = new Scanner(System.in);
                
        System.out.print("Cantidad de equipos a cargar: ");
        int cantEquipos = Integer.parseInt(sc.nextLine());
        
        String sqlEquipo = "INSERT INTO equipos (nombre, puntos) VALUES (?, ?)";

        try (Connection con = Sql.connect(); PreparedStatement ps = con.prepareStatement(sqlEquipo)) {
            for (int i = 0; i < cantEquipos; i++) {
                System.out.print("Ingrese el nombre del equipo #" + (i + 1) + ": ");
                String nombreEquipo = sc.nextLine();

                Equipo equipo = new Equipo(nombreEquipo);

                ps.setString(1, equipo.getNombre());
                ps.setInt(2, 0);
                ps.executeUpdate();
            }
        con.close();
        
        System.out.println("\n[*] Equipos cargados correctamente.");
        Menu.menu();
            
        } catch (SQLException e) {
            System.err.println("\n[!] Error al insertar equipos: " + e.getMessage());
            Menu.menu();
        }    
    }
    
    public static void cargaResultados(){
        Scanner sc = new Scanner(System.in);
        String sqlPartido = "INSERT INTO torneo (equipoLocal, equipoVisitante, golesLocal, golesVisitante) VALUES (?, ?, ?, ?)";

        try (Connection con = Sql.connect(); PreparedStatement ps = con.prepareStatement(sqlPartido); Statement stmt = con.createStatement()) {
            
            System.out.println("--- Partido ---");
            System.out.println("Equipos: ");
            ResultSet rs = stmt.executeQuery("select id, nombre from equipos;");
            
            while (rs.next()) {
                System.out.println(rs.getString(1) + "- " + rs.getString(2) + " ");
            }
            
            System.out.print("Equipo local (ID):");
            String op = sc.nextLine();
            int local = Integer.parseInt(op);
            
            System.out.print("Equipo visitante (ID):");
            String op2 = sc.nextLine();
            int visitante = Integer.parseInt(op2);
            
            System.out.print("Goles de local:");
            String op3 = sc.nextLine();
            int gLocal = Integer.parseInt(op3);
            
            System.out.print("Goles de visitante:");
            String op4 = sc.nextLine();
            int gVisitante = Integer.parseInt(op4);
            
            String sqlEquipo = "UPDATE equipos SET puntos = puntos + 3 WHERE id = ";
            String sqlEquipo2 = "UPDATE equipos SET puntos = puntos + 1 WHERE id = ";
            if (gLocal > gVisitante) {
               String a = String.valueOf(local);
               String sqla = sqlEquipo += a;
                            
               PreparedStatement psE = con.prepareStatement(sqla);
               psE.executeUpdate(); 
            } else if (gVisitante > gLocal) {
                String a = String.valueOf(visitante);
                String sqla = sqlEquipo += a;

                PreparedStatement psE = con.prepareStatement(sqla);
                psE.executeUpdate();
            } else {
               String a = String.valueOf(local);
               String sqla = sqlEquipo2 += a;
                            
               PreparedStatement psE = con.prepareStatement(sqla);
               psE.executeUpdate();
               
               String b = String.valueOf(visitante);
               String sqlb = sqlEquipo2 += b;
                            
               PreparedStatement psEB = con.prepareStatement(sqlb);
               psEB.executeUpdate();
            }
            
            Partido partido = new Partido(local, visitante, gLocal, gVisitante);
            ps.setInt(1, partido.geteLocal());
            ps.setInt(2, partido.geteVisitante());
            ps.setInt(3, partido.getGolesLocal());
            ps.setInt(4, partido.getGolesVisitante());
            ps.executeUpdate();
        
            con.close();
        
            System.out.print("\n[*] Partido cargado correctamente.");
            Menu.menu();
            
        } catch (SQLException e) {
            System.err.println("\n[!] Error al cargar el partido: " + e.getMessage());
            Menu.menu();
        }    
    }
    
    public static void tablaPosiciones(){

        try (Connection con = Sql.connect(); Statement stmt = con.createStatement()) {
            
            System.out.println("\n--- Tabla de posiciones ---");
            ResultSet rs = stmt.executeQuery("select nombre, puntos from equipos order by puntos desc;");
            
            System.out.println("Equipo | Puntos");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " ");
            }
            con.close();
            Menu.menu();
    
        } catch (SQLException e) {
            System.err.println("\n[!] Error al cargar la tabla de posiciones: " + e.getMessage());
            Menu.menu();
        }
    
    }
    
    public static void campeon(){
        try (Connection con = Sql.connect(); Statement stmt = con.createStatement()) {
            
            System.out.println("=== Campeón de liga ===");
            ResultSet rs = stmt.executeQuery("select * from equipos order by puntos desc limit 2;");
            if (rs.next()) {
                int idPrimero = rs.getInt("id");
                String nombrePrimero = rs.getString("nombre");
                int puntosPrimero = rs.getInt("puntos");

                if (rs.next()) {
                    int idSegundo = rs.getInt("id");
                    String nombreSegundo = rs.getString("nombre");
                    int puntosSegundo = rs.getInt("puntos");

                    if (puntosPrimero == puntosSegundo) {
                        System.out.println("Empate entre " + nombrePrimero + " y " + nombreSegundo + " con " + puntosPrimero + " puntos cada uno.");

                        System.out.println("[*] Comprobando diferencia de goles...");
                        ResultSet rsEP = stmt.executeQuery("SELECT SUM(goles) AS totalGoles FROM (SELECT golesLocal AS goles FROM torneo WHERE equipoLocal = " + idPrimero + " UNION ALL SELECT golesVisitante AS goles FROM torneo WHERE equipoVisitante = " + idPrimero + ") AS resultados;");
                        int golesPrimero = 0;
                        if (rsEP.next()){
                            golesPrimero = rsEP.getInt("totalGoles");
                        }

                        ResultSet rsES = stmt.executeQuery("SELECT SUM(goles) AS totalGoles FROM (SELECT golesLocal AS goles FROM torneo WHERE equipoLocal = " + idSegundo + " UNION ALL SELECT golesVisitante AS goles FROM torneo WHERE equipoVisitante = " + idSegundo + ") AS resultados;");
                        int golesSegundo = 0;
                        if (rsES.next()){
                            golesSegundo = rsES.getInt("totalGoles");
                        }
                        
                        if (golesPrimero > golesSegundo) {
                            System.out.println("--- " + nombrePrimero + " con " + puntosPrimero + " puntos ---");
                        } else if (golesSegundo > golesPrimero) {
                            System.out.println("--- " + nombreSegundo + " con " + puntosSegundo + " puntos y " + golesSegundo + " goles ---");
                        } else if (golesSegundo == golesPrimero) {
                            System.out.println("Empate total entre " + nombreSegundo + " y " + nombrePrimero + " con " + golesSegundo + " goles cada uno");
                        } else {
                            System.out.println("\n[!] Error con la lectura de goles.");
                        }

                    } else {
                        System.out.println("--- " + nombrePrimero + " con " + puntosPrimero + " puntos ---");
                    }
                } else {
                    System.out.println("--- " + nombrePrimero + " con " + puntosPrimero + " puntos ---");
                }
            } else {
                System.out.println("\n[!] Error al leer los equipos cargados.");
            }
            con.close();
            Menu.menu();
    
        } catch (SQLException e) {
            System.err.println("\n[!] Error al leer los equipos cargados: " + e.getMessage());
            Menu.menu();
        }
    }
}
