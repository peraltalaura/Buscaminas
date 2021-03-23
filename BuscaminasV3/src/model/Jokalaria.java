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
    private int id;
    private String izena;
    private String denbora;
    
    public Jokalaria(){
        
    }
    public Jokalaria(int id, String izena, String denbora){
        this.id=id;
        this.izena=izena;
        this.denbora=denbora;
    }

    public int getId() {
        return id;
    }

    public String getIzena() {
        return izena;
    }

    public String getDenbora() {
        return denbora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setDenbora(String denbora) {
        this.denbora = denbora;
    }
    
}
