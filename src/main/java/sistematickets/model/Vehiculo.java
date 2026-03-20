
package sistematickets.model;
/**
 @author josec
 */
public abstract class Vehiculo {
    protected String  placa;
    protected Ruta  ruta; //Esperando creacion de la clase ruta
    protected int     capacidadMaxima;
    protected int     pasajerosActuales;
    protected double  tarifaBase;
    protected boolean disponible;
    protected Conductor conductor;  //Esperando creacion de la clase conductor
    
     public Vehiculo(String placa, Ruta ruta) {
        this.placa = placa;
        this.ruta = ruta;
        this.pasajerosActuales=0;
        this.disponible= true;
        this.conductor = null; 
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public int getPasajerosActuales() {
        return pasajerosActuales;
    }

    public void setPasajerosActuales(int pasajerosActuales) {
        this.pasajerosActuales = pasajerosActuales;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public Conductor getConductor() {
        return conductor;
    }
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
    public int getCuposDisponibles() {
        return capacidadMaxima - pasajerosActuales;
    }

     public abstract String getTipo();

     public abstract String toFile();
}
