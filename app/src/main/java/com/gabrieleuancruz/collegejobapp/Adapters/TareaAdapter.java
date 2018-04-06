package com.gabrieleuancruz.collegejobapp.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrieleuancruz.collegejobapp.Classes.TareaClass;
import com.gabrieleuancruz.collegejobapp.R;

import java.util.ArrayList;

/**
 * Created by Gabriel Euan Cruz on 05/04/2018.
 */

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaHolder> {
    ArrayList<TareaClass> ListaTareas;

    public TareaAdapter(ArrayList<TareaClass> listaTareas) {
        this.ListaTareas = listaTareas;
    }

    @Override
    public TareaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tareas_prueba,null,false);
        return new TareaHolder(view);
    }

    @Override
    public void onBindViewHolder(TareaHolder holder, int position) {
        holder.txtTitulo.setText(ListaTareas.get(position).getTitulo());
        holder.txtDescripcion.setText(ListaTareas.get(position).getDescripcion());
        Bitmap bmp = BitmapFactory.decodeByteArray(ListaTareas.get(position).getImagen(), 0, ListaTareas.get(position).getImagen().length);
        holder.imgTarea.setImageBitmap(Bitmap.createScaledBitmap(bmp, holder.imgTarea.getWidth(), holder.imgTarea.getHeight(), false));
    }

    @Override
    public int getItemCount() {
        return ListaTareas.size();
    }

    public class TareaHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo,txtDescripcion;
        ImageView imgTarea;

        public TareaHolder(View itemView) {
            super(itemView);
            txtTitulo= (TextView) itemView.findViewById(R.id.lblTituloTarea);
            txtDescripcion= (TextView) itemView.findViewById(R.id.lblDescripTarea);
            imgTarea = (ImageView) itemView.findViewById(R.id.imgTareaTT);
        }
    }
}
