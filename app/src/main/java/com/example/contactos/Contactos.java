package com.example.contactos;

import java.util.Comparator;

public class Contactos {
    private String nombre, telefono, email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contactos(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
    public static Comparator<Contactos> ContactosComparator
            = new Comparator<Contactos>() {
        @Override
        public int compare(Contactos o1, Contactos o2) {
            //ascending order
            return o1.nombre.compareTo(o2.nombre);
        }
    };
}
