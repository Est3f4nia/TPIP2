package TUP.tpi.modelo;

public class Partido {
    private int eLocal;
    private int eVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido(int eLocal, int eVisitante, int golesLocal, int golesVisitante) {
        this.eLocal = eLocal;
        this.eVisitante = eVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public int geteLocal() {
        return eLocal;
    }

    public int geteVisitante() {
        return eVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }
}
