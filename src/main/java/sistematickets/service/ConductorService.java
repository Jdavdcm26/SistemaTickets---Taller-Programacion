/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import sistematickets.dao.ConductorDao;
import sistematickets.model.Conductor;

/**
 *
 * @author jadis
 */
public class ConductorService {
    
    private ConductorDao conductorDao = new ConductorDao(); 
    
    public boolean agregar(Conductor c) {
        try {
            // Validación del taller: conductor debe tener licencia
            if (c.getNumeroLicencia() == null || c.getNumeroLicencia().isBlank()) {
                return false;
            }
            if (conductorDao.buscarPorCedula(c.getCedula()) != null) {
                return false;
            }
            conductorDao.agregarConductor(c);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Conductor buscarPorCedula(String cedula) {
        try {
            return conductorDao.buscarPorCedula(cedula);
        } catch (IOException e) {
            return null;
        }
    }
}
