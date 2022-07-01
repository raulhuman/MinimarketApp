package com.example.minimarketapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.minimarketapp.R;
import com.example.minimarketapp.models.MiCarritoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PedidoRealizadoActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_realizado);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<MiCarritoModel> list = (ArrayList<MiCarritoModel>) getIntent().getSerializableExtra("itemLista");

        if(list !=null && list.size()>0){
            for(MiCarritoModel model:list){
                //Almacenar datos en un Map
                final HashMap<String, Object> carritoMap = new HashMap<>();

                carritoMap.put("productoNombre", model.getProductoNombre());
                carritoMap.put("productoPrecio", model.getProductoPrecio().toString());
                carritoMap.put("fecha", model.getFecha());
                carritoMap.put("hora", model.getHora());
                carritoMap.put("totalCantidad", model.getTotalCantidad());
                carritoMap.put("totalPrecio", model.getTotalPrecio());

                //Crear la Coleccion "AgregarCarrito", dentro de ella la coleccion "UsuarioActual" y ahi almacena
                //los datos del Map
                firestore.collection("UsuarioActual").document(auth.getCurrentUser().getUid())
                        .collection("MiOrden").add(carritoMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PedidoRealizadoActivity.this, "Tu orden ha sido realizado", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        }
    }
}