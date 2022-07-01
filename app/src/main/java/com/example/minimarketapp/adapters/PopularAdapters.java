package com.example.minimarketapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minimarketapp.R;
import com.example.minimarketapp.activities.VerTodoActivity;
import com.example.minimarketapp.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public PopularAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapters.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.nombre.setText(popularModelList.get(position).getNombre());
        holder.rating.setText(popularModelList.get(position).getRating());
        holder.descripcion.setText(popularModelList.get(position).getDescripcion());
        holder.descuento.setText(popularModelList.get(position).getDescuento());

        //Dar clic a cualquier item, pasar datos
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VerTodoActivity.class);
                intent.putExtra("tipo", popularModelList.get(position).getTipo());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView nombre, descripcion, rating, descuento;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            nombre = itemView.findViewById(R.id.pop_name);
            descripcion = itemView.findViewById(R.id.pop_des);
            rating = itemView.findViewById(R.id.pop_rating);
            descuento = itemView.findViewById(R.id.pop_disscount);
        }
    }
}
