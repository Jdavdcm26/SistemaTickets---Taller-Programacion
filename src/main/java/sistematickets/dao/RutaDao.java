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
import sistematickets.model.Ruta;
import sistematickets.util.RutaArchivos;

/**
 *
 * @author josec
 */
public class RutaDao {
      public void guardarRutas(HashMap<String, Ruta> rutas) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(RutaArchivos.RUTAS));
        File archivo = new File(RutaArchivos.RUTAS);
        if (!archivo.exists()) {
        archivo.createNewFile(); // crea el archivo si no existe
    }
        for (Ruta r : rutas.values()) {
            bw.write(r.toFile());
            bw.newLine();
        }
        bw.close();
        
    }
        public HashMap<String, Ruta> cargarRutas() throws IOException {
        HashMap<String, Ruta> rutas = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(RutaArchivos.RUTAS));
        File archivo = new File(RutaArchivos.RUTAS);
        if (!archivo.exists()) {
        return rutas;
        }
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] p = linea.split(";");
            Ruta r = new Ruta(p[0], p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4]));
            rutas.put(r.getCodRuta(), r);
             }
        br.close();
        return rutas;
    }  
}
