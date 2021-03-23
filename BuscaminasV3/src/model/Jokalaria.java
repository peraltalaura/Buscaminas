/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author peralta.laura
 */
public class Jokalaria {
    private String izena;
    private String denbora;
    
    public Jokalaria(){
        
    }
    public Jokalaria(String izena, String denbora){
        this.izena=izena;
        this.denbora=denbora;
    }

    public String getIzena() {
        return izena;
    }

    public String getDenbora() {
        return denbora;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setDenbora(String denbora) {
        this.denbora = denbora;
    }
    
}
