package org.example.dao;

import org.example.domain.Alojamiento;
import org.example.domain.CasaRural;
import org.example.domain.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {


    private final ArrayList<Alojamiento> alojamientos;

    public Database() {
        this.alojamientos = new ArrayList<>();
        //String nombre, String provincia, double precio, ArrayList<Integer> valoraciones,boolean piscina
        /*alojamientos.add(new Hotel("Mario", "Madrid", 45, new ArrayList<>(), 5));
        alojamientos.add(new Hotel("David", "Murcia", 30, new ArrayList<>(), 3));
        alojamientos.add(new Hotel("Carlota", "Madrid", 63, new ArrayList<>(), 4));
        alojamientos.add(new CasaRural("Miguel", "Valencia", 20, new ArrayList<>(), true));
        alojamientos.add(new CasaRural("Jorge", "Madrid", 25, new ArrayList<>(), false));
        alojamientos.add(new CasaRural("Ahmed", "Santiago", 12, new ArrayList<>(), true));
        Random r = new Random();
        for (Alojamiento alojamiento : alojamientos) {
            alojamiento.getValoraciones().add(r.nextInt(6));
            alojamiento.getValoraciones().add(r.nextInt(6));
            alojamiento.getValoraciones().add(r.nextInt(6));
            alojamiento.getValoraciones().add(r.nextInt(6));
        }*/
    }

    public Database (ArrayList<Alojamiento> alojamientos){
        this.alojamientos = alojamientos;
    }

    public List<Alojamiento> getListaAlojamientos() {
        return alojamientos;
    }


    public void setListaAlojamientos(List<Alojamiento> alojamientos) {
        this.alojamientos.clear();
        this.alojamientos.addAll(alojamientos);
    }
}
