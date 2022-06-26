package com.example.minimarketapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minimarketapp.R;
import com.example.minimarketapp.models.RecomendadoModel;

import java.util.List;

public class RecomendadoAdapter extends RecyclerView.Adapter<RecomendadoAdapter.ViewHolder> {

    Context context;
    List<RecomendadoModel> list;

    public RecomendadoAdapter(Context context, List<RecomendadoModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //reemplaza vista por el cardview
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recomendado_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.nombre.setText(list.get(position).getNombre());
        holder.descripcion.setText(list.get(position).getDescripcion());
        holder.rating.setText(list.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nombre, descripcion, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rec_img);
            nombre = itemView.findViewById(R.id.rec_name);
            descripcion = itemView.findViewById(R.id.rec_des);
            rating = itemView.findViewById(R.id.rec_rating);
        }
    }
}
