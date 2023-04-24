package org.example.dao;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.example.common.ComparacionPorCategoriaNombre;
import org.example.domain.Alojamiento;
import org.example.domain.Hotel;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DaoAlojamientosTest {
    @InjectMocks
    DaoAlojamientosImpl daoAlojamientos;

    @Mock
    Database database;

    @Test
    void getListaAlojamientos() {

        //given
        List<Alojamiento> lista = new ArrayList<>();
        lista.add(new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5));
        lista.add(new Hotel("David", "Murcia", 30, new ArrayList<>(), 3));

        //when
        when(database.getListaAlojamientos()).thenReturn(lista);

        List<Alojamiento> result = daoAlojamientos.getListaAlojamientos();

        //then
        assertAll(
                () -> assertThat(result).isEqualTo(lista),
                () -> assertThat(result).isNotNull()
        );
    }

    @Nested
    class AddAlojamiento {
        @Test
        void addAlojamientoVacio() {
            //given
            Hotel hotel = new Hotel("hotel1", "Lugo", 100.3, new ArrayList<>(List.of(3, 5, 4)), 5);
            //when
            when(database.getListaAlojamientos()).thenReturn(new ArrayList<>());
            boolean respuesta = daoAlojamientos.addAlojamiento(hotel);

            //then
            assertThat(respuesta).isEqualTo(true);
        }

        @Test
        void addAlojamientoNoVacio() {
            //given
            Hotel hotel = new Hotel("hotel1", "Lugo", 100.3, new ArrayList<>(List.of(3, 5, 4)), 5);
            ArrayList<Alojamiento> alojamientos = new ArrayList<>();
            alojamientos.add(new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5));
            alojamientos.add(new Hotel("David", "Murcia", 30, new ArrayList<>(), 3));
            alojamientos.add(new Hotel("Carlota", "Madrid", 63, new ArrayList<>(), 4));

            //when
            when(database.getListaAlojamientos()).thenReturn(alojamientos);
            boolean respuesta = daoAlojamientos.addAlojamiento(hotel);
            //then
            assertThat(respuesta).isEqualTo(true);
        }
    }

    @Test
    void consulta() {
        //given
        List<Alojamiento> alojamientos = new ArrayList<>();
        var hotelMad = new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5);
        var hotelMur = new Hotel("David", "Murcia", 30, new ArrayList<>(), 3);
        alojamientos.add(hotelMad);
        alojamientos.add(hotelMur);

        String province = "Madrid";
        double p1 = 40;
        double p2 = 50;

        //when
        when(database.getListaAlojamientos()).thenReturn(alojamientos);

        List<Alojamiento> respuesta = daoAlojamientos.consulta(province, p1, p2);

        //then
        assertAll(
                () -> Assertions.assertThat(respuesta).contains(hotelMad)
        );

    }

    @Test
    void alojamientosPorValoracionMedia() {
        //given
        List<Alojamiento> lista = new ArrayList<>();
        Hotel hotel1 = new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5);
        Hotel hotel2 = new Hotel("David", "Murcia", 30, new ArrayList<>(), 1);
        Hotel hotel3 = new Hotel("Sanchez", "Madrid", 50, new ArrayList<>(), 2);
        Hotel hotel4 = new Hotel("David", "Murcia", 30, new ArrayList<>(), 4);
        lista.add(hotel1);
        lista.add(hotel2);
        lista.add(hotel3);
        lista.add(hotel4);
        Random r = new Random();
        for (Alojamiento alojamiento : lista) {
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
        }
        String province = "Madrid";

        //when
        when(database.getListaAlojamientos()).thenReturn(lista);
        List<Alojamiento> respuesta = daoAlojamientos.alojamientosPorValoracionMedia(province);

        //then
        assertAll(
                () -> Assertions.assertThat(respuesta).contains(hotel1),
                () -> Assertions.assertThat(respuesta).contains(hotel3),
                () -> assertThat(respuesta).isNotEqualTo(lista),
                () -> Assertions.assertThat(respuesta).size().isEqualTo(2));
    }

    @Test
    void actualizarCategoria() {
        //given
        List<Alojamiento> lista = new ArrayList<>();
        Hotel hotel1 = new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5);
        lista.add(hotel1);

        String nombreHotel = "Mario";
        int nuevaCat = 2;

        //when
        when(database.getListaAlojamientos()).thenReturn(lista);
        boolean result = daoAlojamientos.actualizarCategoria(nombreHotel, nuevaCat);

        //then
        assertTrue(result);

    }

    @Test
    void consultaHoteles() {
        //given
        List<Alojamiento> lista = new ArrayList<>();
        Hotel hotel1 = new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5);
        Hotel hotel2 = new Hotel("David", "Murcia", 30, new ArrayList<>(), 1);
        Hotel hotel3 = new Hotel("Sanchez", "Madrid", 50, new ArrayList<>(), 2);
        Hotel hotel4 = new Hotel("David", "Murcia", 30, new ArrayList<>(), 4);
        lista.add(hotel1);
        lista.add(hotel2);
        lista.add(hotel3);
        lista.add(hotel4);
        Random r = new Random();
        for (Alojamiento alojamiento : lista) {
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
        }
        boolean ascendente = true;

        //when
        when(database.getListaAlojamientos()).thenReturn(lista);
        var result = daoAlojamientos.consultaHoteles(ascendente);

        //then
        assertAll(
                () -> AssertionsForClassTypes.assertThat(lista).isNotEqualTo(result),
                () -> AssertionsForClassTypes.assertThat(lista.stream().filter(Hotel.class::isInstance)
                        .map(Hotel.class::cast)
                        .sorted(ascendente ? new ComparacionPorCategoriaNombre() : new ComparacionPorCategoriaNombre().reversed())
                        .collect(Collectors.toList())).isEqualTo(result)
        );
    }


    @Test
    void getListaAlojamientosProvincia() {
        //given
        List<Alojamiento> lista = new ArrayList<>();
        Hotel hotel1 = new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5);
        Hotel hotel2 = new Hotel("David", "Murcia", 30, new ArrayList<>(), 1);
        Hotel hotel3 = new Hotel("Sanchez", "Madrid", 50, new ArrayList<>(), 2);
        Hotel hotel4 = new Hotel("David", "Murcia", 30, new ArrayList<>(), 4);
        lista.add(hotel1);
        lista.add(hotel2);
        lista.add(hotel3);
        lista.add(hotel4);
        Random r = new Random();
        for (Alojamiento alojamiento : lista) {
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
            alojamiento.getValoraciones().add(r.nextInt(1, 6));
        }
        String province = "Murcia";
        String wrongProvince = "Madrid";

        //when
        when(database.getListaAlojamientos()).thenReturn(lista);
        List<Alojamiento> result = daoAlojamientos.getListaAlojamientosProvincia(province);

        //then
        assertAll(
                () -> AssertionsForClassTypes.assertThat(lista).isNotEqualTo(result),
                () -> Assertions.assertThat(result).doesNotContainAnyElementsOf(lista.stream()
                        .filter(alojamiento -> alojamiento.getProvincia().equals(wrongProvince))
                        .collect(Collectors.toList()))
        );

    }

    @Test
    void removeAlojamiento() {
        //given
        List<Alojamiento> alojamientoLista = new ArrayList<>();
        Hotel hotel1 = new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5);
        Hotel hotel2 = new Hotel("David", "Murcia", 30, new ArrayList<>(), 1);
        alojamientoLista.add(hotel1);
        alojamientoLista.add(hotel2);

        //when
        when(database.getListaAlojamientos()).thenReturn(alojamientoLista);
        daoAlojamientos.removeAlojamiento(hotel1);

        //then
        assertAll(
                () -> Assertions.assertThat(alojamientoLista).doesNotContain(hotel1),
                () -> Assertions.assertThat(alojamientoLista).contains(hotel2)
        );
        verify(database, times(1)).getListaAlojamientos();
    }

    @Test
    void isEmptyAlojamientosListVacio() {

        //given
        List<Alojamiento> lista = new ArrayList<>();

        //when
        when(database.getListaAlojamientos()).thenReturn(lista);
        boolean result = daoAlojamientos.isEmptyAlojamientosList();

        //then
        assertTrue(result);

    }

    @Test
    void isEmptyAlojamientosListLleno() {

        //given
        List<Alojamiento> lista = new ArrayList<>();
        lista.add(new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5));

        //when
        when(database.getListaAlojamientos()).thenReturn(lista);
        boolean result = daoAlojamientos.isEmptyAlojamientosList();

        //then
        assertFalse(result);

    }

    @Test
    void setAlojamientos() {
        //given
        List<Alojamiento> lista = new ArrayList<>();
        lista.add(new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5));

        //when
        daoAlojamientos.setAlojamientos(lista);

        //then
        verify(database, times(1)).setListaAlojamientos(lista);
    }

}