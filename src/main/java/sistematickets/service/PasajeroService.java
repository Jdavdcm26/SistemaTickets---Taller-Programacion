/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import sistematickets.dao.ConductorDao;
import sistematickets.dao.PasajeroDao;
import sistematickets.model.Pasajero;
import sistematickets.model.PasajeroAdultoMayor;
import sistematickets.model.PasajeroEstudiante;
import sistematickets.model.PasajeroRegular;

/**
 *
 * @author jadis
 */
public class PasajeroService {
    
    private PasajeroDao pasajeroDao = new PasajeroDao();
    private ConductorDao conductorDao = new ConductorDao();

    public boolean agregar(String cedula, String nombre, LocalDate fechaNacimiento, String tipo) {
        try {
            if (pasajeroDao.buscarPorCedula(cedula) != null || conductorDao.buscarPorCedula(cedula) != null) {
                return false;
            }
            // Taller 2: calcular edad automáticamente
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            Pasajero p;
            if (edad >= 60) {
                p = new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
            } else {
                p = switch (tipo.toLowerCase()) {
                    case "estudiante" -> new PasajeroEstudiante(cedula, nombre, fechaNacimiento);
                    default           -> new PasajeroRegular(cedula, nombre, fechaNacimiento);
                };
            }
            pasajeroDao.agregarPasajero(p);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Pasajero buscarPorCedula(String cedula) {
        try {
            return pasajeroDao.buscarPorCedula(cedula);
        } catch (IOException e) {
            return null;
        }
    }
    
    public ArrayList<Pasajero> listarTodos() {
        try {
            return pasajeroDao.cargarLista();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    
    public boolean actualizar(String cedula, String nombre, LocalDate fechaNacimiento, String tipo) {
    try {
        HashMap<String, Pasajero> pasajeros = pasajeroDao.cargarPasajeros();
        if (!pasajeros.containsKey(cedula)) {
            return false;
        }
        // Calcular nueva edad
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        Pasajero pActualizado;
        if (edad >= 60) {
            pActualizado = new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
        } else {
            pActualizado = switch (tipo.toLowerCase()) {
                case "estudiante" -> new PasajeroEstudiante(cedula, nombre, fechaNacimiento);
                default           -> new PasajeroRegular(cedula, nombre, fechaNacimiento);
            };
        }
        pasajeros.put(cedula, pActualizado);
        pasajeroDao.guardarPasajeros(pasajeros);
        return true;
    } catch (IOException e) {
        return false;
    }
}

    public boolean eliminar(String cedula) {
        try {
            HashMap<String, Pasajero> pasajeros = pasajeroDao.cargarPasajeros();
            if (!pasajeros.containsKey(cedula)) {
                return false;
            }
            pasajeros.remove(cedula);
            pasajeroDao.guardarPasajeros(pasajeros);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
}
