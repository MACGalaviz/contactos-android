package com.example.contactos;

import android.net.Uri;

import java.util.Comparator;

public class Contactos {
    private String nombre, telefono, email;
    private Uri imageUri;

    public Contactos(String nombre, String telefono, String email, Uri imageUri) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.imageUri = imageUri;
    }

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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
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
