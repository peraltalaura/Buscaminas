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
    private int denbora;

    public Jokalaria(String izena, int denbora) {
        this.izena = izena;
        this.denbora = denbora;
    }

    public String getIzena() {
        return izena;
    }

    public int getDenbora() {
        return denbora;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setDenbora(int denbora) {
        this.denbora = denbora;
    }
    public void setJokalaria(Jokalaria j){
        this.izena = j.izena;
        this.denbora = j.denbora;
    }

}
