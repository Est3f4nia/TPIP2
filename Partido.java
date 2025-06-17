public class Partido {
    private String eLocal;
    private String eVisitante;
    private int golesLocal;
    private int golesVisitante;
    private String ganador;

    public Partido() {
    }

    public Partido(String eLocal, String eVisitante, int golesLocal, int golesVisitante, String ganador) {
        this.eLocal = eLocal;
        this.eVisitante = eVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.ganador = ganador;
    }

    public String geteLocal() {
        return eLocal;
    }

    public String geteVisitante() {
        return eVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public String getGanador() {
        return ganador;
    }

    public void seteLocal(String eLocal) {
        this.eLocal = eLocal;
    }

    public void seteVisitante(String eVisitante) {
        this.eVisitante = eVisitante;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    
}
