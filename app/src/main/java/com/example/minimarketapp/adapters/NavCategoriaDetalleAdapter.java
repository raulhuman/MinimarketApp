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
import com.example.minimarketapp.models.NavCategoriaDetalleModel;

import java.util.List;

public class NavCategoriaDetalleAdapter extends RecyclerView.Adapter<NavCategoriaDetalleAdapter.ViewHolder> {

    Context context;
    List<NavCategoriaDetalleModel> list;

    public NavCategoriaDetalleAdapter(Context context, List<NavCategoriaDetalleModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_categoria_detalle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.nombre.setText(list.get(position).getNombre());
        holder.precio.setText(list.get(position).getPrecio()+"/docena");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nombre, precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cat_nav_img);
            nombre = itemView.findViewById(R.id.nav_cat_name);
            precio = itemView.findViewById(R.id.price);
        }
    }
}
