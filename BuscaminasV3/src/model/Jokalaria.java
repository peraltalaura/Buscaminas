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
    private int laukiak;

    public Jokalaria() {

    }

    public Jokalaria(String izena, int denbora, int laukiak) {
        this.izena = izena;
        this.denbora = denbora;
        this.laukiak = laukiak;
    }

    public int getLaukiak() {
        return laukiak;
    }

    public String getIzena() {
        return izena;
    }

    public int getDenbora() {
        return denbora;
    }

    public void setLaukiak(int laukiak) {
        this.laukiak = laukiak;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setDenbora(int denbora) {
        this.denbora = denbora;
    }

    public void setJokalaria(Jokalaria j) {
        this.setIzena(j.izena);
        this.setDenbora(j.denbora);
        this.setLaukiak(j.laukiak);
    }

}
