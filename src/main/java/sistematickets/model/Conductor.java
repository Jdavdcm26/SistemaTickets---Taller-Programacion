/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.model;

/**
 *
 * @author jadis
 */
public class Conductor extends Persona {
    
    private String numeroLicencia;
    private String categoriaLicencia;

    public Conductor( String cedula, String nombre, String numeroLicencia, String categoriaLicencia) {
        super(cedula, nombre);
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }

    
    public String getNumeroLicencia() { 
        return numeroLicencia; 
    }
    
    public void setNumeroLicencia(String numeroLicencia) { 
        this.numeroLicencia = numeroLicencia; 
    }
    
    public String getCategoriaLicencia() { 
        return categoriaLicencia; 
    }
    
    public void setCategoriaLicencia(String cat) { 
        this.categoriaLicencia = cat; 
    }
    
     @Override
    public String imprimirDetalle() {
        return "Cédula            : " + cedula + "\n" +
               "Nombre            : " + nombre + "\n" +
               "N° Licencia       : " + numeroLicencia + "\n" +
               "Categoría licencia: " + categoriaLicencia;
    }

    @Override
    public String toFile() {
        return cedula + ";" + nombre + ";" + numeroLicencia + ";" + categoriaLicencia;
    }
    
}
