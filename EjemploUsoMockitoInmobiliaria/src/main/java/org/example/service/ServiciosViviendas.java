package org.example.service;

import org.example.common.m2Exception;
import org.example.domain.Vivienda;

import java.io.IOException;
import java.util.List;

public interface ServiciosViviendas {
    boolean isEmptyAlojamientosList();
    List<Vivienda> getListaAlojamientos();
    boolean addAlojamiento(Vivienda vivienda)throws m2Exception;
    List<Vivienda> consulta(String provincia, double precio1, double precio2);
    void removeAlojamiento(Vivienda vivienda);
    List<Vivienda> alojamientosPorValoracionMedia(String provincia);

    boolean actualizarCategoria(String nombre, int categoria) throws m2Exception;

    List<Vivienda> consultaViviendas(boolean ascendente);
    List<Vivienda> getListaAlojamientosProvincia(String provincia);


}
