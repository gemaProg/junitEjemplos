package org.example.dao;

import org.example.domain.Vivienda;

import java.util.List;

public interface DaoViviendas {


    List<Vivienda> getListaviviendas() ;


    boolean addvivienda(Vivienda vivienda) ;

    List<Vivienda> consulta(String provincia, double precio1, double precio2) ;
    List<Vivienda> viviendasPorValoracionMedia(String provincia) ;

    boolean actualizarCategoria(String nombre, int categoria) ;

    List<Vivienda> listadoOrdenadoPisos(boolean ascendente);

    List<Vivienda> getListaviviendasProvincia(String provincia) ;

    void removevivienda(Vivienda vivienda) ;

    void setviviendas(List<Vivienda> viviendas);
    boolean isEmptyviviendasList() ;
}
