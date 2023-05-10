package org.example.service;

import org.example.common.m2Exception;
import org.example.common.Comprobacion;
import org.example.dao.DaoViviendas;
import org.example.dao.DaoViviendasImpl;
import org.example.domain.Vivienda;

import java.io.IOException;
import java.util.List;

public class ServiciosViviendasImpl implements ServiciosViviendas{

    private final DaoViviendas daoViviendas;


    public ServiciosViviendasImpl() {
        this.daoViviendas = new DaoViviendasImpl();

    }
    public ServiciosViviendasImpl(DaoViviendas daoViviendas) {
        this.daoViviendas = daoViviendas;
    }

    public boolean isEmptyViviendasList(){
        return daoViviendas.isEmptyViviendasList();
    }
    public List<Vivienda> getListaViviendas() {
        return daoViviendas.getListaViviendas();
    }

    public boolean addVivienda(Vivienda vivienda) throws m2Exception {
         Comprobacion.m2Ok(vivienda.getM2());
         return daoViviendas.addVivienda(vivienda);
     }

    public List<Vivienda> consulta(String provincia, double precio1, double precio2) {
        if (precio1 > precio2) {
            double aux = precio1;
            precio1 = precio2;
            precio2 = aux;
        }
        return daoViviendas.consulta(provincia, precio1, precio2);
    }


    public boolean actualizarCategoria(String nombre, int categoria) throws m2Exception {
        Comprobacion.m2Ok(categoria);
        return daoViviendas.actualizarCategoria(nombre, categoria);
    }


    public List<Vivienda> consultaViviendas(boolean ascendente) {
        return daoViviendas.listadoOrdenadoViviendasCalle(ascendente);
    }

    public List<Vivienda> getListaViviendasProvincia(String provincia) {
        return daoViviendas.getListaviviendasProvincia(provincia);
    }

    public void removeVivienda(Vivienda vivienda) {
        daoViviendas.removeVivienda(vivienda);;
    }

    public void crearFicheros() throws IOException {
        DaoViviendasFicheros.crearFicheros();
    }
    public boolean cargarFichero() throws IOException {
        return cargarFichero(DaoViviendasFicheros.FICHERO);
    }
    public boolean cargarFichero(String fichero) throws IOException {
        boolean cargado = false;
        List<Vivienda> viviendas = DaoViviendasFicheros.leerFichero(fichero);
        if (viviendas != null  && viviendas.size()!=0) {
            daoViviendas.setViviendas(viviendas);
            cargado = true;
        }
        return cargado;
    }

    public boolean escribirFichero() {
        return DaoViviendasFicheros.escribirFichero(daoViviendas.getListaViviendas());
    }

    public boolean escribirFicheroBinario() {
        return DaoViviendasFicheros.escribirFicheroBinario(daoViviendas.getListaViviendas());
    }

    public boolean cargarFicheroBinario() {
        boolean cargado = false;
        List<Vivienda> viviendas =DaoViviendasFicheros.leerFicheroBinario();
        if (viviendas != null) {
            daoViviendas.setViviendas(viviendas);
            cargado = true;
        }
        return cargado;
    }
}

