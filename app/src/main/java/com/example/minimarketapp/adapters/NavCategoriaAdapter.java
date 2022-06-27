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
import com.example.minimarketapp.models.NavCategoriaModel;

import java.util.List;

public class NavCategoriaAdapter extends RecyclerView.Adapter<NavCategoriaAdapter.ViewHolder> {

    Context context;
    List<NavCategoriaModel> list;

    public NavCategoriaAdapter(Context context, List<NavCategoriaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.nombre.setText(list.get(position).getNombre());
        holder.descripcion.setText(list.get(position).getDescripcion());
        holder.descuento.setText(list.get(position).getDescuento());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nombre, descripcion, descuento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cat_nav_img);
            nombre = itemView.findViewById(R.id.nav_cat_name);
            descripcion = itemView.findViewById(R.id.nav_cat_description);
            descuento = itemView.findViewById(R.id.nav_cat_discount);
        }
    }
}
