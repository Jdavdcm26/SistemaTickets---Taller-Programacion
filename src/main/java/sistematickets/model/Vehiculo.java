
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
}
