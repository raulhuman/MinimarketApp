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
import com.example.minimarketapp.models.VerTodoModel;

import java.util.List;

public class VerTodoAdapter extends RecyclerView.Adapter<VerTodoAdapter.ViewHolder> {

    Context context;
    List<VerTodoModel> list;

    public VerTodoAdapter(Context context, List<VerTodoModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_todo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.nombre.setText(list.get(position).getNombre());
        holder.descripcion.setText(list.get(position).getDescripcion());
        holder.rating.setText(list.get(position).getRating());
        holder.precio.setText(list.get(position).getPrecio()+"/kg");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nombre, descripcion, precio, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.view_img);
            nombre = itemView.findViewById(R.id.view_name);
            descripcion = itemView.findViewById(R.id.view_descripcion);
            precio = itemView.findViewById(R.id.view_price);
            rating = itemView.findViewById(R.id.view_rating);
        }
    }
}
