package com.example.contactos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // boleano para resetear lista
    boolean reset;

    // Arraylist
    ArrayList<Contactos> listaContactos = new ArrayList<Contactos>();

    // Declaración de variables
    ListView lvContactos;
    Button btnAgregarContacto;
    Button btnReset;
    Button btnAgregarContactos;

    // Declaración de adaptador
    Adaptador adaptador;

    // Creación objeto de clase ArchivoTexto
    ArchivoTexto archivo = new ArchivoTexto(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Relación java-XML
        lvContactos = findViewById(R.id.lvContactos);
        btnAgregarContacto = findViewById(R.id.btnAgregarContacto);
        btnAgregarContacto.setOnClickListener(this);
        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
        btnAgregarContactos = findViewById(R.id.btnAgregarContactos);
        btnAgregarContactos.setOnClickListener(this);
        /*// Agregamos datos Todo Temporal
        llenadoDeContactos();*/

        // Si el archivo de texto no está vacío
        if (archivo.leer().length()!=0){
            // Separar archivo por -
            String[] arreglo = archivo.leer().split("\\|");

//            // Conocer el tamaño del arreglo TODO Temporal
//            Toast.makeText(this,String.valueOf(arreglo.length), Toast.LENGTH_SHORT).show();
//
//            // Iterar para conocer el contenido del arreglo; TODO Temporal
//            for (int i = 0; i<=arreglo.length-1; i++) {
//                Toast.makeText(this, arreglo[i], Toast.LENGTH_SHORT).show();
//            }

            // Iterar el arreglo para alimentar ArrayList de clase Contactos
            for (int i = 0; i<=arreglo.length-1; i+=3)
            {
                listaContactos.add(new Contactos(arreglo[i],arreglo[i+1],arreglo[i+2]));
            }
           /* // Iterar ArrayList para conocer el contenido TODO Temporal
            for (int i=0; i<=listaContactos.size()-1; i++)
            {
                Toast.makeText(this,"Nombre: "+listaContactos.get(i).getNombre()
                        +" Telefóno: "+listaContactos.get(i).getTelefono()
                        +" Email: "+listaContactos.get(i).getEmail(), Toast.LENGTH_SHORT).show();
            }*/
            Collections.sort(listaContactos,Contactos.ContactosComparator);
        }



        // Crear adaptador
        adaptador = new Adaptador(this,listaContactos);

        // Asignar el adaptador a listview
        lvContactos.setAdapter(adaptador);

        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* Toast.makeText(MainActivity.this, listaContactos.get(position).getNombre(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, listaContactos.get(position).getTelefono(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, listaContactos.get(position).getEmail(), Toast.LENGTH_SHORT).show();*/
                Intent w = new Intent(MainActivity.this,DetallesContacto.class);
                w.putExtra("nombre",listaContactos.get(position).getNombre());
                w.putExtra("telefono",listaContactos.get(position).getTelefono());
                w.putExtra("email", listaContactos.get(position).getEmail());
                w.putExtra("position", String.valueOf(position));
                //startActivity(w);
                startActivityForResult(w,2);
            }
        });
        registerForContextMenu(lvContactos);
    }

   /* // Metodo para agregar contactos // TODO Temporal
     public void llenadoDeContactos(){
        listaContactos.add(new Contactos("MarcoBachoco","6871685308","marcoa789@hotmail.com"));
        listaContactos.add(new Contactos("xxx","6871685308","marcoa789@hotmail.com"));
        listaContactos.add(new Contactos("yyy","6871685308","marcoa789@hotmail.com"));
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAgregarContacto:
                Intent i = new Intent(MainActivity.this,EditarContacto.class);
                startActivityForResult(i,1);
                break;

            case R.id.btnReset:
                AlertDialog.Builder siono = new AlertDialog.Builder(this);
                siono.setTitle("Eliminar Contactos");
                siono.setMessage("¿Estás seguro de que deseas borrar todos los contactos?");
                siono.setCancelable(false);
                siono.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface siono, int which) {
                    }
                });
                siono.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface siono, int which) {
                        reset = true;
                        Intent r = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(r);
                    }
                });
                siono.show();
                break;
            case R.id.btnAgregarContactos:
                AlertDialog.Builder siono1 = new AlertDialog.Builder(this);
                siono1.setTitle("Agregar Contactos por Defecto");
                siono1.setMessage("¿Estás seguro de que deseas agregar los contactos por defecto?");
                siono1.setCancelable(false);
                siono1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface siono1, int which) {
                    }
                });
                siono1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface siono1, int which) {
                        listaContactos.add(new Contactos("Juan Perez","668111116","perecito@hotmail.com"));
                        listaContactos.add(new Contactos("Santa Ana","66333337","santanita@hotmail.com"));
                        listaContactos.add(new Contactos("Maria Elena","6683455662","mariaelenita@hotmail.com"));
                        listaContactos.add(new Contactos("José David","6698237642344","davidsitop8@hotmail.com"));
                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                });
                siono1.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if (resultCode == RESULT_OK)
                {
                    // Variables para obtener los datos // TODO Me gustaria quitarlo
                    String nombre = data.getExtras().getString("nombre");
                    String telefono = data.getExtras().getString("telefono");
                    String email = data.getExtras().getString("email");
                    // Agregar datos a la lista de contactos
                    listaContactos.add(new Contactos(nombre,telefono,email));
                    /*// Toas para ver si se reciben los datos // TODO Temporal
                    Toast.makeText(this, nombre+telefono+email, Toast.LENGTH_SHORT).show();*/
                    Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    //Toast.makeText(this, "Se canceló la petición de agregar", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (resultCode == RESULT_OK)
                {
                    // Variables para obtener los datos // TODO Me gustaria quitarlo
                    String nombre = data.getExtras().getString("nombre");
                    String telefono = data.getExtras().getString("telefono");
                    String email = data.getExtras().getString("email");
                    String position = data.getExtras().getString("position");
                    // Agregar datos a la lista de contactos
                    listaContactos.get(Integer.valueOf(position)).setNombre(nombre);
                    listaContactos.get(Integer.valueOf(position)).setTelefono(telefono);
                    listaContactos.get(Integer.valueOf(position)).setEmail(email);
                    /*// Toas para ver si se reciben los datos // TODO Temporal
                    Toast.makeText(this, nombre+telefono+email, Toast.LENGTH_SHORT).show();*/
                    Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    //Toast.makeText(this, "Se canceló la petición de agregar", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.lvContactos){

            AdapterView.AdapterContextMenuInfo infoMenu = (AdapterView.AdapterContextMenuInfo) menuInfo;
            ArrayList<String> elementosDelMenu = new ArrayList<>();
            elementosDelMenu.add("Editar");
            elementosDelMenu.add("Eliminar");
            elementosDelMenu.add("Llamar");
            elementosDelMenu.add("Email");
            elementosDelMenu.add("SMS");
            //elementosDelMenu = getResources().getStringArray(R.array.ElementosMenuContextual);
            for (int i = 0; i<=elementosDelMenu.size()-1; i++)
            {
                menu.add(Menu.NONE,i,i,elementosDelMenu.get(i));
            }
            menu.setHeaderTitle(listaContactos.get(infoMenu.position).getNombre());

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo infoContacto = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ArrayList<String> elementosDelMenu = new ArrayList<>();
        elementosDelMenu.add("Editar");
        elementosDelMenu.add("Eliminar");
        elementosDelMenu.add("Llamar");
        elementosDelMenu.add("Email");
        elementosDelMenu.add("SMS");
        int posicionDelElementoDelMenuSeleccionado = item.getItemId();
        switch (posicionDelElementoDelMenuSeleccionado)
        {
            case 0:
                Intent i = new Intent(MainActivity.this,EditarContacto.class);
                i.putExtra("nombre",listaContactos.get(infoContacto.position).getNombre());
                i.putExtra("telefono",listaContactos.get(infoContacto.position).getTelefono());
                i.putExtra("email", listaContactos.get(infoContacto.position).getEmail());
                i.putExtra("position", String.valueOf(infoContacto.position));
                startActivityForResult(i,2);
                break;
            case 1:
                final int fposition = infoContacto.position;
                AlertDialog.Builder siono = new AlertDialog.Builder(this);
                siono.setTitle("Eliminar Contacto");
                siono.setMessage("¿Estás seguro de que deseas eliminar este contacto?");
                siono.setCancelable(false);
                siono.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface siono, int which) {
                    }
                });
                siono.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface siono, int which) {
                        listaContactos.remove(fposition);
                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                });
                siono.show();
                break;
            case 2:
                // Aquí Llamar

                /*String dial = "tel:" + Integer.valueOf(listaContactos.get(infoContacto.position).getTelefono());
                Toast.makeText(this, "dial", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));*/
                String dial = "tel:" +listaContactos.get(infoContacto.position).getTelefono();
                // Mostrar dial
                // Toast.makeText(this, dial, Toast.LENGTH_SHORT).show();

                Intent tel = new Intent(android.content.Intent.ACTION_DIAL,
                        Uri.parse(dial));
                startActivity(tel);
                break;
            case 3:
                // Aquí Email
                //Toast.makeText(this, "Aqui email", Toast.LENGTH_SHORT).show();
                String correo = listaContactos.get(infoContacto.position).getEmail();  // The number on which you want to send SMS
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",correo, null));
                startActivity(emailIntent);
                break;
            case 4:
                // Aquí SMS
                //Toast.makeText(this, "Aqui sms", Toast.LENGTH_SHORT).show();
                /*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_to", "default content");
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);*/
                String number = listaContactos.get(infoContacto.position).getTelefono();  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                break;
        }

        //return super.onContextItemSelected(item);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!reset){
            // Crear/Recrear archivo de texto vacío, para simular el guardado de contactos y evitar la duplicidad de datos
            archivo.crear("");

            // Agregar todos los contactos del ArrayList ListaContactos al archivo de texto
            for (int i = 0; i <=listaContactos.size()-1;i++)
            {
                archivo.agregar(listaContactos.get(i).getNombre()
                        +"|"+listaContactos.get(i).getTelefono()
                        +"|"+listaContactos.get(i).getEmail()+"|");
                /*// Mostrar contenido del ArrayList listaContactos al ser iterado TODO Temporal
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();*/
            }
        }else{
            // Crear/Recrear archivo de texto vacío, para simular el guardado de contactos y evitar la duplicidad de datos
            archivo.crear("");
        }

    }




}
