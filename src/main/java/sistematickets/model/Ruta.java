/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematickets.model;

/**
 *
 * @author josec
 */
public class Ruta implements InterfazImprimible {
    private String codRuta;
    private String ciuOrigen;
    private String ciuDestino;
    private int kilometros;
    private int tiemReco;
    
    public Ruta(String codRuta, String ciuOrigen, String ciuDestino, int kilometros, int tiemReco) {
        this.codRuta = codRuta;
        this.ciuOrigen = ciuOrigen;
        this.ciuDestino = ciuDestino;
        this.kilometros = kilometros;
        this.tiemReco = tiemReco;
    }

    public String getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(String codRuta) {
        this.codRuta = codRuta;
    }

    public String getCiuOrigen() {
        return ciuOrigen;
    }

    public void setCiuOrigen(String ciuOrigen) {
        this.ciuOrigen = ciuOrigen;
    }

    public String getCiuDestino() {
        return ciuDestino;
    }

    public void setCiuDestino(String ciuDestino) {
        this.ciuDestino = ciuDestino;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public int getTiemReco() {
        return tiemReco;
    }

    public void setTiemReco(int tiemReco) {
        this.tiemReco = tiemReco;
    }
    
    @Override
    public String imprimirDetalle(){
        return "Código de ruta  : " + codRuta + "\n" +
               "Ciudad origen   : " + ciuOrigen + "\n" +
               "Ciudad destino  : " + ciuDestino + "\n" +
               "Kilómetros      : " + kilometros + " km\n" +
               "Tiempo recorrido: " + tiemReco + " min";
    }

    @Override
    public String toFile() {
        return  codRuta + ";" + ciuOrigen + ";" + ciuDestino + ";" + kilometros + ";" + tiemReco;
    }
    
}