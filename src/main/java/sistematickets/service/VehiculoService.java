/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import java.util.ArrayList;
import sistematickets.dao.ConductorDao;
import sistematickets.dao.RutaDao;
import sistematickets.dao.VehiculoDao;
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
}
