package com.example.contactos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    Context contexto;
    ArrayList<Contactos> listaContactos;

    public Adaptador(Context contexto, ArrayList<Contactos> listaContactos) {
        this.contexto = contexto;
        this.listaContactos = listaContactos;
    }

    @Override
    public int getCount() {
        return listaContactos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaContactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layoutAdapter = convertView;
        if (layoutAdapter==null){

            LayoutInflater inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutAdapter = inflador.inflate(R.layout.layout_adapter,parent,false);
        }

        TextView tvName = layoutAdapter.findViewById(R.id.tvName);
        TextView tvPhone = layoutAdapter.findViewById(R.id.tvPhone);
        TextView tvEmail = layoutAdapter.findViewById(R.id.tvEmail);
        ImageView ivImagen = layoutAdapter.findViewById(R.id.ivImagen);

        tvName.setText(listaContactos.get(position).getNombre());
        tvPhone.setText("Teléfono: "+listaContactos.get(position).getTelefono());
        tvEmail.setText("E-mail: "+listaContactos.get(position).getEmail());
        ivImagen.setImageURI(listaContactos.get(position).getImageUri());

        return layoutAdapter;
    }
}
