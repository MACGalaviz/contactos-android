package com.example.contactos;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ArchivoTexto {

    private String nombreArchivo = "arrays.txt";
    private Context contexto;

    public ArchivoTexto(Context c)
    {
        contexto = c;
    }
    public void crear(String texto)
    {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(contexto.openFileOutput(nombreArchivo, Activity.MODE_PRIVATE));
            osw.write(texto);
            osw.flush();
            osw.close();
        }catch (IOException e){}
    }

    public void agregar(String texto)
    {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(contexto.openFileOutput(nombreArchivo,Activity.MODE_APPEND));
            osw.write(texto);
            osw.flush();
            osw.close();
        }catch (IOException e){}
    }

    public String leer(){
        String cadena ="";
        try{
            InputStreamReader isr = new InputStreamReader(contexto.openFileInput(nombreArchivo));
            BufferedReader br = new BufferedReader(isr);
            String linea = br.readLine();
            while (linea != null)
            {
                // Cuidado aquí no conozco el salto de linea que genera este paso
                // Se cambiará la siguiente linea
                // cadena = cadena + linea+"\n";
                // Por
                // cadena = cadena + linea;
                // Para esperar un mejor funcionamiento o mejor entendimiento del programa a la hora de utilizar el metodo split();
                cadena = cadena + linea;
                linea = br.readLine();
            }
            br.close();
            isr.close();
        }catch (IOException e){}
        return cadena;
    }
}
