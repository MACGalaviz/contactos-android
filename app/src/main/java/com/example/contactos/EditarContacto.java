package com.example.contactos;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarContacto extends AppCompatActivity implements View.OnClickListener {
    // Declaración de variable para el resultado del uso de la galería
    public static final int RESULT_GALLERY = 0;

    EditText etNombre, etTelefono, etEmail;
    Button btnOk, btnCancelar;

    String nombre ;
    String telefono;
    String email ;
    String position;
    // Declaración de variable para la url de la imagen
    Uri imageUri;

    // Definición de variables
    Button btnImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);
        // vincular java - xml
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
        btnImagen = findViewById(R.id.btnImagen);
        btnImagen.setOnClickListener(this);
        try {
            Bundle datos = getIntent().getExtras();
            // Variables para obtener los datos // TODO Me gustaria quitarlo
            nombre = datos.getString("nombre");
            telefono = datos.getString("telefono");
            email = datos.getString("email");
            position = datos.getString("position");
            // Toas para ver si se reciben los datos // TODO Temporal
            etNombre.setText(nombre);
            etTelefono.setText(telefono);
            etEmail.setText(email);
            imageUri = Uri.parse(datos.getString("imageUri"));
            //Toast.makeText(this, nombre+telefono+email+position, Toast.LENGTH_SHORT).show();
        }catch(Exception e){}
        //Toast.makeText(this, position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnOk:
                if (!etNombre.getText().toString().equals("")
                        && !etTelefono.getText().toString().equals("")
                        && !etEmail.getText().toString().equals("")
                        && !imageUri.toString().equals("")){

                    Intent a = new Intent(EditarContacto.this, MainActivity.class);

                    nombre = etNombre.getText().toString();
                    telefono = etTelefono.getText().toString();
                    email = etEmail.getText().toString();

                    nombre = nombre.replace("|","");
                    telefono = telefono.replace("|","");
                    email = email.replace("|","");
                    Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this,telefono, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, email, Toast.LENGTH_SHORT).show();

                    a.putExtra("nombre", nombre);
                    a.putExtra("telefono", telefono);
                    a.putExtra("email", email);
                    a.putExtra("position", position);
                    a.putExtra("imageUri",imageUri.toString());
                    setResult(RESULT_OK, a);
                    finish();
                }
                break;
            case R.id.btnCancelar:
                Intent b = new Intent(EditarContacto.this,MainActivity.class);
                setResult(RESULT_CANCELED,b);
                finish();
                break;

            case R.id.btnImagen:
                Intent galleryIntent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); startActivityForResult(galleryIntent , RESULT_GALLERY );
                break;
        }
    }

    // Metodo para obtener la url de la imagen
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_GALLERY :
                if (null != data) {
                    // Obtener la url de la imagen
                    imageUri = data.getData();
                    Toast.makeText(this, imageUri.toString(), Toast.LENGTH_SHORT).show();
                } break;
            default:
                break;
        }
    }
}
