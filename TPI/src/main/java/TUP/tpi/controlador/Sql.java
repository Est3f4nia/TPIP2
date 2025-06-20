package TUP.tpi.controlador;

import TUP.tpi.vista.Menu;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;

public class Sql {   
    
    public static Connection connect() {
        ObjectMapper om = new ObjectMapper();
        Scanner sc = new Scanner(System.in);
        Connection con = null;

        System.out.println("\n--- Conexión a base de datos ---");

        while (con == null) {
            System.out.print("Usuario: ");
            String user = sc.nextLine();

            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/liga", user, pass);
                System.out.println("[*] Conectando a base de datos...");
                System.out.println("[*] Conectado.");

                Statement stmt = con.createStatement();
                try {
                    File file = new File("src\\main\\java\\TUP\\tpi\\modelo\\comp.json");
                    Map<String, Object> comp = om.readValue(file, Map.class);

                    if ((Boolean) comp.get("empty")) {
                        System.out.println("[*] Creando tabla: equipos...");
                        stmt.executeUpdate("CREATE TABLE equipos(id int NOT NULL PRIMARY KEY AUTO_INCREMENT, nombre varchar(50) NOT NULL, puntos int NOT NULL);");

                        System.out.println("[*] Creando tabla: jugadores...");
                        stmt.executeUpdate("CREATE TABLE jugadores(dni int NOT NULL PRIMARY KEY, nombre varchar(50) NOT NULL, posicion varchar(10) NOT NULL, equipo int NOT NULL REFERENCES equipos (id));");

                        System.out.println("[*] Creando tabla: torneo...");
                        stmt.executeUpdate("CREATE TABLE torneo(id int NOT NULL PRIMARY KEY AUTO_INCREMENT, equipoLocal int NOT NULL REFERENCES equipos (id) ON DELETE CASCADE, equipoVisitante int NOT NULL REFERENCES equipos (id) ON DELETE CASCADE, golesLocal int NOT NULL, golesVisitante int NOT NULL);");

                        comp.put("empty", false);
                        om.writeValue(file, comp);
                    }

                } catch (IOException e) {
                    System.out.println("\n[!] Problema con el archivo comp.json");
                    Menu.menu();
                }

            } catch (Exception e) {
                System.out.println("\n[!] Problema al conectar: posible error de credenciales.");
                con = null;
            }
        }
        return con;
    }
}
