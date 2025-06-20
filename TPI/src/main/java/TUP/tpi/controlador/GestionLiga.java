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
    
    public static void cargaJugadores(){
        Scanner sc = new Scanner(System.in);
                
        System.out.print("Jugadores del mismo equipo a ingresar: ");
        int cantJugadores = Integer.parseInt(sc.nextLine());
        
        String sqlJugadores = "INSERT INTO jugadores (dni, nombre, posicion, equipo) VALUES (?, ?, ?, ?)";

        try (Connection con = Sql.connect(); PreparedStatement ps = con.prepareStatement(sqlJugadores); Statement stmt = con.createStatement()) {
            System.out.println("Seleccione un equipo:");
            ResultSet rs = stmt.executeQuery("select id, nombre from equipos;");
            
            System.out.println("ID | Equipo");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "- " + rs.getString(2) + " ");
            }
            
            System.out.print("Equipo (ID): ");
            String op = sc.nextLine();
            int idEquipo = Integer.parseInt(op);
                        
            for (int i = 0; i < cantJugadores; i++) {
                
                System.out.print("DNI del jugador #" + (i + 1) + ": ");
                String d = sc.nextLine();
                int dni = Integer.parseInt(d);
                
                System.out.print("Nombre completo: ");
                String nombreJugador = sc.nextLine();
                
                System.out.println("Posición: ");
                System.out.println("1- Delantero.");
                System.out.println("2- Mediocampista.");
                System.out.println("3- Defensor.");
                System.out.println("4- Arquero.");
                System.out.print("Opción: ");
                String p = sc.nextLine();
                int po = Integer.parseInt(p);
                String posicion = "";
                if (po == 1){
                    posicion = "Delantero";
                } else if (po == 2){
                    posicion = "Mediocampista";
                } else if (po == 3){
                    posicion = "Defensor";
                } else if (po == 4){
                    posicion = "Arquero";
                } else {
                    System.out.println("[!] Opción no válida.");
                    Menu.menu();
                }

                Jugador jugador = new Jugador(dni, nombreJugador, posicion, idEquipo);
                ps.setInt(1, jugador.getDni());
                ps.setString(2, jugador.getNombre());
                ps.setString(3, jugador.getPosicion());
                ps.setInt(4, idEquipo);
                ps.executeUpdate();
            }
        con.close();
        
        System.out.println("\n[*] Jugadores cargados correctamente.");
        Menu.menu();
            
        } catch (SQLException e) {
            System.err.println("\n[!] Error al insertar jugadores: " + e.getMessage());
            Menu.menu();
        }    
    }
    
    public static void buscarJugador(){
        Scanner sc = new Scanner(System.in);
                
        System.out.print("DNI: ");
        int idJugador = Integer.parseInt(sc.nextLine());

        try (Connection con = Sql.connect(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT J.nombre, posicion, E.nombre AS nombreEquipo FROM jugadores AS J JOIN equipos AS E ON J.equipo = E.id WHERE dni = " + idJugador);
            
            if (!rs.isBeforeFirst()){
                System.out.println("\n[!] No se encuentra al jugador en el sistema.");
                con.close();
                Menu.menu();
            } else {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " - " + rs.getString(2) + ": " + rs.getString(3) + " ");
                }
                con.close();
                Menu.menu();
            }
        } catch (SQLException e){
            System.out.println("\n[!] Error con la búsqueda de jugador:" + e.getMessage());
            Menu.menu();
        }
    }
