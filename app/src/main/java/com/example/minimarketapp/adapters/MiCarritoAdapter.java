package com.example.minimarketapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimarketapp.R;
import com.example.minimarketapp.models.MiCarritoModel;

import java.util.List;

public class MiCarritoAdapter extends RecyclerView.Adapter<MiCarritoAdapter.ViewHolder> {

    Context context;
    List<MiCarritoModel> carritoModelList;
    int totalPrecio = 0;

    public MiCarritoAdapter(Context context, List<MiCarritoModel> carritoModelList) {
        this.context = context;
        this.carritoModelList = carritoModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mi_carrito_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nombre.setText(carritoModelList.get(position).getProductoNombre());
        holder.precio.setText(carritoModelList.get(position).getProductoPrecio());
        holder.fecha.setText(carritoModelList.get(position).getFecha());
        holder.hora.setText(carritoModelList.get(position).getHora());
        holder.cantidad.setText(carritoModelList.get(position).getTotalCantidad());
        holder.totalPrecio.setText(String.valueOf(carritoModelList.get(position).getTotalPrecio()));

        //Pasar el total acumulado a CarritoFragment
        totalPrecio = totalPrecio + carritoModelList.get(position).getTotalPrecio();
        Intent intent = new Intent("MiTotalAcumulado");
        intent.putExtra("totalAcumulado", totalPrecio);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return carritoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, precio, fecha, hora, cantidad, totalPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.product_name);
            precio = itemView.findViewById(R.id.product_price);
            fecha = itemView.findViewById(R.id.current_date);
            hora = itemView.findViewById(R.id.current_time);
            cantidad = itemView.findViewById(R.id.total_quantity);
            totalPrecio = itemView.findViewById(R.id.total_price);
        }
    }
}
