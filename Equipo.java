package com.mycompany.gestionliga;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private String nombre;
    private int puntos;
    private int diferenciaGoles;
    //Lista de jugadores por equipo (Agregar en SQL)

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.diferenciaGoles = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }


    public void actualizarPuntos(int puntosGanados, int golesAFavor, int golesEnContra) {
        this.puntos += puntosGanados;
        this.diferenciaGoles += (golesAFavor - golesEnContra);
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    @Override
    public String toString() {
        return "Equipo [Nombre: " + nombre + ", Puntos: " + puntos + ", Dif. Goles: " + diferenciaGoles + "]";
    }
}
}
