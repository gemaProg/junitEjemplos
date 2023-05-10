package org.example.dao;

import org.example.domain.Vivienda;

import java.util.ArrayList;
import java.util.List;

public class Database {


    private final ArrayList<Vivienda> viviendas;

    public Database() {
        this.viviendas = new ArrayList<>();
    }

    public Database (ArrayList<Vivienda> viviendas){
        this.viviendas = viviendas;
    }

    public List<Vivienda> getListaViviendas() {
        return viviendas;
    }


    public void setListaViviendas(List<Vivienda> viviendas) {
        this.viviendas.clear();
        this.viviendas.addAll(viviendas);
    }
}
