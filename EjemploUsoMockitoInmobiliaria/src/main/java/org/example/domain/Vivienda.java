/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.domain;

import java.io.Serializable;


/**
 *
 * @author examen
 */
public class Vivienda implements Serializable, Comparable<Vivienda> {

    private String calle;
    private int numero;
    private String provincia;
    protected double precio;
    protected double m2;

    public Vivienda(String calle, int numero, String provincia, double precio, double m2) {
        this.calle = calle;
        this.numero = numero;
        this.provincia = provincia;
        this.precio = precio;
        this.m2 = m2;
    }

    public double getM2() {
        return m2;
    }

    public void setM2(double m2) {
        this.m2 = m2;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vivienda{" +
                "calle='" + calle + '\'' +
                ", numero=" + numero +
                ", provincia='" + provincia + '\'' +
                ", precio=" + precio +
                ", m2=" + m2 +
                '}';
    }


    @Override
    public int compareTo(Vivienda arg0) {
        return this.calle.compareTo(arg0.calle);
    }

}
