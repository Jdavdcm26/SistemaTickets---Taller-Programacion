/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import sistematickets.dao.ConductorDao;
import sistematickets.dao.RutaDao;
import sistematickets.dao.VehiculoDao;
import sistematickets.model.Conductor;
import sistematickets.model.Vehiculo;

/**
 *
 * @author josec
 */
public class VehiculoService {
    private VehiculoDao vehiculoDao  = new VehiculoDao();
    private RutaDao rutaDao          = new RutaDao();
    private ConductorDao conductorDao = new ConductorDao();

    public boolean agregar(Vehiculo v) {
        try {
            if (vehiculoDao.buscarPorPlaca(v.getPlaca(), rutaDao, conductorDao) != null) {
                return false; // placa duplicada
            }
            vehiculoDao.agregarVehiculo(v);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Vehiculo buscarPorPlaca(String placa) {
        try {
            return vehiculoDao.buscarPorPlaca(placa, rutaDao, conductorDao);
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayList<Vehiculo> listarTodos() {
        try {
            return vehiculoDao.cargarLista(rutaDao, conductorDao);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public ArrayList<Vehiculo> buscarPorRuta(String codRuta) {
        try {
            return vehiculoDao.cargarLista(rutaDao, conductorDao).stream()
                    .filter(v -> v.getRuta().getCodRuta().equals(codRuta) && v.isDisponible())
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public boolean asignarConductor(String placa, String cedulaConductor) {
        try {
            HashMap<String, Vehiculo> vehiculos = vehiculoDao.cargarTodos(rutaDao, conductorDao);
            Vehiculo v = vehiculos.get(placa);
            if (v == null) return false;

            Conductor c = conductorDao.buscarPorCedula(cedulaConductor);
            if (c == null) return false;

            // Validación: conductor debe tener licencia
            if (c.getNumeroLicencia() == null || c.getNumeroLicencia().isBlank()) return false;

            v.setConductor(c);
            vehiculoDao.guardarTodos(vehiculos);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean eliminar(String placa) {
        try {
            HashMap<String, Vehiculo> vehiculos = vehiculoDao.cargarTodos(rutaDao, conductorDao);
            if (!vehiculos.containsKey(placa)) return false;
            vehiculos.remove(placa);
            vehiculoDao.guardarTodos(vehiculos);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
