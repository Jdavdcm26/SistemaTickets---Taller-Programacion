/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import sistematickets.dao.RutaDao;
import sistematickets.model.Ruta;

/**
 *
 * @author josec
 */
public class RutaService {
    private RutaDao rutaDao = new RutaDao();
    public boolean agregar(Ruta r) {
        try {
            if (rutaDao.buscarPorCodigo(r.getCodRuta()) != null) {
                return false;
            }
            rutaDao.agregarRuta(r);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public Ruta buscarPorCodigo(String codRuta) {
        try {
            return rutaDao.buscarPorCodigo(codRuta);
        } catch (IOException e) {
            return null;
        }
    }
    public ArrayList<Ruta> listarTodas() {
        try {
            return rutaDao.cargarLista();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public boolean eliminar(String codRuta) {
        try {
            HashMap<String, Ruta> rutas = rutaDao.cargarRutas();
            if (!rutas.containsKey(codRuta)) {
                return false;
            }
            rutas.remove(codRuta);
            rutaDao.guardarRutas(rutas);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public boolean actualizar(Ruta r) {
        try {
            HashMap<String, Ruta> rutas = rutaDao.cargarRutas();
            if (!rutas.containsKey(r.getCodRuta())) {
                return false;
            }
            rutas.put(r.getCodRuta(), r);
            rutaDao.guardarRutas(rutas);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
