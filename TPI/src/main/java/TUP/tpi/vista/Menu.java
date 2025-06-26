package TUP.tpi.vista;

import java.util.Scanner;
import TUP.tpi.controlador.GestionLiga;

public class Menu {
    public static void menu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Cargar equipos.");
        System.out.println("2. Cargar jugadores.");
        //System.out.println("x. Cargar amarillas.");
        System.out.println("3. Mostrar información de jugador.");
        System.out.println("4. Ingresar resultados de partido.");
        System.out.println("5. Ver tabla de posiciones.");
        System.out.println("6. Ver campeón.");
        System.out.println("0. Salir");

        System.out.print("Opción: ");

        int opcion;
        while (true) {
            try {
                String entrada = sc.nextLine();
                opcion = Integer.parseInt(entrada);
                if (opcion >= 0 && opcion <= 6) {
                    break;
                } else {
                    System.out.println("\n[!] Opción inválida.");
                    Menu.menu();
                }
            } catch (NumberFormatException e) {
                System.out.print("\n[!] No es un número válido.");
                Menu.menu();
            }
        }

        if (opcion == 1) {
            GestionLiga.cargaEquipos();
        }
        
        if (opcion == 2) {
            GestionLiga.cargaJugadores();
        }
        
        if (opcion == 3){
            GestionLiga.buscarJugador();
        }
        
        if (opcion == 4){
            GestionLiga.cargaResultados();
        }
        
        if (opcion == 5){
            GestionLiga.tablaPosiciones();
        }
        
        if (opcion == 6){
            GestionLiga.campeon();
        }

        if (opcion == 0){
            System.out.println("[*] Saliendo...");
        }
        
    }
}

