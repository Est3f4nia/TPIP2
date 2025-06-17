package com.mycompany.gestionliga;

public class Partido {

    private Equipo eLocal;
    private Equipo eVisitante;
    private int golesLocal;
    private int golesVisitante;
    private Equipo ganador;

    public Partido(Equipo eLocal, Equipo eVisitante, int golesLocal, int golesVisitante) {
        this.eLocal = eLocal;
        this.eVisitante = eVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        definirGanador();
    }

    public void definirGanador() {
        if (golesLocal > golesVisitante) {
            this.ganador = eLocal;
        } else if (golesVisitante > golesLocal) {
            this.ganador = eVisitante;
        } else {
            this.ganador = null;
        }
    }

    public Equipo geteLocal() {
        return eLocal;
    }

    public Equipo geteVisitante() {
        return eVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public Equipo getGanador() {
        return ganador;
    }

    @Override
    public String toString() {
        return "Partido: " + eLocal.getNombre() + " (" + golesLocal + ") vs " +
               eVisitante.getNombre() + " (" + golesVisitante + ") - Ganador: " +
               (ganador != null ? ganador.getNombre() : "Empate");
    }
}
