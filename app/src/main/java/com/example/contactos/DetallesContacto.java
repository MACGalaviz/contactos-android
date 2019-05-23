/*package com.example.contactos;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesContacto extends AppCompatActivity implements View.OnClickListener {
    TextView tvNombre;
    Button btnLlamar,btnEmail,btnEditar;

    String nombre ;
    String telefono;
    String email ;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_contacto);
        tvNombre = findViewById(R.id.tvNombre);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnLlamar.setOnClickListener(this);
        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);
        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLlamar:
                break;
            case R.id.btnEmail:
                break;
            case R.id.btnEditar:
                Intent i = new Intent(DetallesContacto.this,EditarContacto.class);

                startActivityForResult(i,2);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 2:
                if (resultCode == RESULT_OK)
                {
                    // TODO Código de que se editó
                    String nombre = data.getExtras().getString("nombre");
                    tvNombre.setText(nombre);
                    Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // TODO Se canceló
                }
                break;
            case 3:
                if (resultCode == RESULT_OK)
                {
                    // Variables para obtener los datos // TODO Me gustaria quitarlo
                    nombre = data.getExtras().getString("nombre");
                    telefono = data.getExtras().getString("telefono");
                    email = data.getExtras().getString("email");
                    position = data.getExtras().getString("position");
                    // Toas para ver si se reciben los datos // TODO Temporal
                    tvNombre.setText(nombre);
                    Toast.makeText(this, nombre+telefono+email+position, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // TODO Se canceló
                }
                break;
        }
    }
}*/
package com.example.contactos;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesContacto extends AppCompatActivity implements View.OnClickListener {
    TextView tvNombre;
    Button btnLlamar,btnEmail,btnEditar,btnSms;
    String nombre ;
    String telefono;
    String email ;
    String position;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_contacto);
        tvNombre = findViewById(R.id.tvNombre);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnLlamar.setOnClickListener(this);
        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);
        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);
        btnSms = findViewById(R.id.btnSms);
        btnSms.setOnClickListener(this);

        try {
            Bundle datos = getIntent().getExtras();
            // Variables para obtener los datos // TODO Me gustaria quitarlo
            nombre = datos.getString("nombre");
            telefono = datos.getString("telefono");
            email = datos.getString("email");
            position = datos.getString("position");
            imageUri = datos.getString("imageUri");
            // Toas para ver si se reciben los datos // TODO Temporal
            tvNombre.setText(nombre);

            //Toast.makeText(this, nombre+telefono+email+position, Toast.LENGTH_SHORT).show();
        }catch(Exception e){}

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLlamar:
                String dial = "tel:" +telefono;
                // Mostrar dial
                // Toast.makeText(this, dial, Toast.LENGTH_SHORT).show();

                Intent tel = new Intent(android.content.Intent.ACTION_DIAL,
                        Uri.parse(dial));
                startActivity(tel);
                break;
            case R.id.btnEmail:
                String correo = email;  // The number on which you want to send SMS
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",correo, null));
                startActivity(emailIntent);
                break;
            case R.id.btnEditar:
                Intent i = new Intent(DetallesContacto.this,EditarContacto.class);
                i.putExtra("nombre",nombre);
                i.putExtra("telefono",telefono);
                i.putExtra("email", email);
                i.putExtra("position", position);
                i.putExtra("imageUri", imageUri);

                startActivityForResult(i,2);
                break;
            case R.id.btnSms:
                String number = telefono;  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 2:
                if (resultCode == RESULT_OK)
                {
                    Intent a = new Intent(DetallesContacto.this,MainActivity.class);

                    a.putExtra("nombre",data.getExtras().getString("nombre"));
                    a.putExtra("telefono",data.getExtras().getString("telefono"));
                    a.putExtra("email",data.getExtras().getString("email"));
                    a.putExtra("position",data.getExtras().getString("position"));
                    a.putExtra("imageUri",data.getExtras().getString("imageUri"));
                    setResult(RESULT_OK,a);
                    finish();
                }
                else
                {
                    // TODO Se canceló
                }
                break;
            case 3:
                if (resultCode == RESULT_OK)
                {
                    /*// Variables para obtener los datos // TODO Me gustaria quitarlo
                    nombre = data.getExtras().getString("nombre");
                    telefono = data.getExtras().getString("telefono");
                    email = data.getExtras().getString("email");
                    position = data.getExtras().getString("position");
                    // Toas para ver si se reciben los datos // TODO Temporal
                    tvNombre.setText(nombre);
                    Toast.makeText(this, nombre+telefono+email+position, Toast.LENGTH_SHORT).show();*/
                }
                else
                {
                    // TODO Se canceló
                }
                break;
        }
    }
}
