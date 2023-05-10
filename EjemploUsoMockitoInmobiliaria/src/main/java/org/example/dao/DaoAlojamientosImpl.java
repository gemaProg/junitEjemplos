package org.example.dao;

import org.example.common.ComparacionPorCalleNumero;
import org.example.common.m2Exception;
import org.example.common.ComparacionPorCategoriaNombre;
import org.example.common.Comprobacion;
import org.example.domain.Vivienda;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DaoViviendasImpl implements DaoViviendas {

    private final Database database;

    public DaoViviendasImpl() {
        this.database = new Database();
    }

    public DaoViviendasImpl(Database database) {
        this.database = database;
    }

    public List<Vivienda> getListaViviendas() {
        return database.getListaViviendas();
    }


    public boolean addVivienda(Vivienda vivienda) {
        List<Vivienda> lista = database.getListaViviendas();
        return lista.add(vivienda);
    }

    public List<Vivienda> consulta(String provincia, double precio1, double precio2) {

        return database.getListaViviendas().stream().filter(Vivienda -> Vivienda.getProvincia().equals(provincia)
                && Vivienda.getPrecio() >= precio1
                && Vivienda.getPrecio() <= precio2).collect(Collectors.toList());
    }

    public List<Vivienda> ViviendasPorCalleNumero(String provincia) {

        return database.getListaViviendas().stream()
                .filter(Vivienda -> Vivienda.getProvincia().equals(provincia))
                .sorted(new ComparacionPorCalleNumero())
                .collect(Collectors.toList());

    }

    public boolean actualizarm2(String nombre, double m2) {
        Vivienda h = database.getListaViviendas().stream()
                .filter(hotel -> hotel.getCalle().equals(nombre)).findFirst().orElse(null);
        if (h != null) {
            try {
                Comprobacion.m2Ok(m2);
                h.setM2(m2);
                return true;
            } catch (m2Exception e) {
                throw new RuntimeException(e);
            }

        }

        return false;
    }

    public List<Vivienda> listadoOrdenadoViviendasCalle(boolean ascendente) {
        List<Vivienda> viviendas = database.getListaViviendas().stream().sorted().toList();
        if (ascendente)
            Collections.reverse(viviendas);
        return viviendas;
    }

    public List<Vivienda> getListaViviendasProvincia(String provincia) {
        return database.getListaViviendas().stream()
                .filter(Vivienda -> Vivienda.getProvincia().equals(provincia))
                .collect(Collectors.toList());
    }

    public void removeVivienda(Vivienda vivienda) {
        database.getListaViviendas().remove(vivienda);
    }

    public void setViviendas(List<Vivienda> viviendas) {
        database.setListaViviendas(viviendas);
    }


    public boolean isEmptyViviendasList() {
        return database.getListaViviendas().isEmpty();
    }
}
