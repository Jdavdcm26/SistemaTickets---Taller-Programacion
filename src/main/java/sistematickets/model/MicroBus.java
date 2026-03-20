package sistematickets.model;


public class MicroBus extends Vehiculo {

    public MicroBus(String placa, Ruta ruta) {
        super(placa, ruta);
        this.tarifaBase=10000;
        this.capacidadMaxima=25;
    }

    @Override
    public String getTipo() {
        return "MicroBus";
    }

    @Override
    public String imprimirDetalle() {
        return "Tipo : " + getTipo() +
                "\nPlaca : " + placa + "\n" +
               ruta.imprimirDetalle() + "\n" +
               "Tarifa base : " + tarifaBase + "\n" +
               "Pasajeros : " + pasajerosActuales + " personas\n" +
               "Capacidad maxima : " + capacidadMaxima + " personas"+
               "\nCupos disponibles : " + getCuposDisponibles() + " personas" + // NUEVO
               "\nDisponible : " + (disponible ? "Sí" : "No") +
               "\nConductor : " + (conductor != null ? conductor.getNombre() : "Sin conductor asignado"); // NUEVO
    }

    @Override
    public String toFile() {
        return placa + ";" + ruta.getCodRuta() + ";" + tarifaBase + ";" + pasajerosActuales + ";" + capacidadMaxima + ";" + disponible + ";" + (conductor != null ? conductor.getCedula() : "null"); // NUEVO
    }
    
}
