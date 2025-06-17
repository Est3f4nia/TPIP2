package com.mycompany.gestionliga;

public class Jugador {

    private String nombre;
    private String posicion;
    private boolean amonestado;
    private boolean expulsado;

    public Jugador(String nombre, String posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.amonestado = false;
        this.expulsado = false;
    }

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

    public void setAmonestado(boolean amonestado) {
        this.amonestado = amonestado;
    }

    public void setExpulsado(boolean expulsado) {
        this.expulsado = expulsado;
    }

    @Override
    public String toString() {
        String estado = "";
        if (amonestado) estado += " (Amonestado)";
        if (expulsado) estado += " (Expulsado)";
        return "Jugador [Nombre: " + nombre + ", Posición: " + posicion + estado + "]";
    }
}


   
   
