/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import sistematickets.model.Bus;
import sistematickets.model.Buseta;
import sistematickets.model.MicroBus;
import sistematickets.model.Ruta;
import sistematickets.model.Vehiculo;
import sistematickets.util.RutaArchivos;

/**
 *
 * @author josec
 */
public class VehiculoDao {
    public void agregarVehiculo(Vehiculo v) throws IOException {
        File archivo = new File(getArchivo(v.getTipo()));
        if (!archivo.exists()) archivo.createNewFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(v.toFile());
            bw.newLine();
        }
    }

    public HashMap<String, Vehiculo> cargarTodos(RutaDao rutaDao, ConductorDao conductorDao) throws IOException {
        HashMap<String, Vehiculo> vehiculos = new HashMap<>();
        cargarArchivo(RutaArchivos.BUSES,      "Bus",      vehiculos, rutaDao, conductorDao);
        cargarArchivo(RutaArchivos.BUSETAS,     "Buseta",   vehiculos, rutaDao, conductorDao);
        cargarArchivo(RutaArchivos.MICROBUSES,  "MicroBus", vehiculos, rutaDao, conductorDao);
        return vehiculos;
    }
    private String getArchivo(String tipo) {
        return switch (tipo) {
            case "Bus"      -> RutaArchivos.BUSES;
            case "Buseta"   -> RutaArchivos.BUSETAS;
            default         -> RutaArchivos.MICROBUSES;
        };
    }
    private void cargarArchivo(String archivo, String tipo, HashMap<String, Vehiculo> mapa,
                                RutaDao rutaDao, ConductorDao conductorDao) throws IOException {
        File f = new File(archivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                // p[0]=placa, p[1]=codRuta, p[2]=tarifaBase
                // p[3]=pasajeros, p[4]=capacidad, p[5]=disponible, p[6]=cedulaConductor
                Ruta r = rutaDao.buscarPorCodigo(p[1]);
                Vehiculo v = switch (tipo) {
                    case "Bus"      -> new Bus(p[0], r);
                    case "Buseta"   -> new Buseta(p[0], r);
                    default         -> new MicroBus(p[0], r);
                };
                v.setPasajerosActuales(Integer.parseInt(p[3]));
                v.setDisponible(Boolean.parseBoolean(p[5]));
                if (!p[6].equals("null")) {
                    v.setConductor(conductorDao.buscarPorCedula(p[6]));
                }
                mapa.put(v.getPlaca(), v);
            }
        }
    }
    public void guardarTodos(HashMap<String, Vehiculo> vehiculos) throws IOException {
        HashMap<String, Vehiculo> buses      = new HashMap<>();
        HashMap<String, Vehiculo> busetas    = new HashMap<>();
        HashMap<String, Vehiculo> microbuses = new HashMap<>();

        for (Vehiculo v : vehiculos.values()) {
            switch (v.getTipo()) {
                case "Bus"      -> buses.put(v.getPlaca(), v);
                case "Buseta"   -> busetas.put(v.getPlaca(), v);
                case "MicroBus" -> microbuses.put(v.getPlaca(), v);
            }
        }
        guardarArchivo(RutaArchivos.BUSES,     buses);
        guardarArchivo(RutaArchivos.BUSETAS,    busetas);
        guardarArchivo(RutaArchivos.MICROBUSES, microbuses);
    }

    private void guardarArchivo(String ruta, HashMap<String, Vehiculo> mapa) throws IOException {
        File archivo = new File(ruta);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Vehiculo v : mapa.values()) {
                bw.write(v.toFile());
                bw.newLine();
            }
        }
    }
}
