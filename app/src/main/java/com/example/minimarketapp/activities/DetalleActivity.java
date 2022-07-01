package com.example.minimarketapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.minimarketapp.R;
import com.example.minimarketapp.models.VerTodoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetalleActivity extends AppCompatActivity {

    TextView cantidad;
    int totalCantidad = 1;
    int totalPrecio = 0;

    ImageView detalleImg;
    TextView precio, rating, descripcion;
    Button agregarCarrito;
    ImageView agregarItem, removerItem;
    Toolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    VerTodoModel verTodoModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        //Traer lo datos y guardarlos en un Object
        final Object object = getIntent().getSerializableExtra("detalle");
        if(object instanceof VerTodoModel){
            verTodoModel = (VerTodoModel) object;
        }

        cantidad = findViewById(R.id.quantity);
        detalleImg = findViewById(R.id.detailed_img);
        agregarItem = findViewById(R.id.add_item);
        removerItem = findViewById(R.id.remove_item);

        precio = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        descripcion = findViewById(R.id.detailed_description);

        //Colocar los datos en el layout
        if(verTodoModel !=null){
            Glide.with(getApplicationContext()).load(verTodoModel.getImg_url()).into(detalleImg);
            rating.setText(verTodoModel.getRating());
            descripcion.setText(verTodoModel.getDescripcion());
            precio.setText("Precio: $"+ verTodoModel.getPrecio()+"/Kg");

            totalPrecio = verTodoModel.getPrecio()* totalCantidad;
        }

        if(verTodoModel.getTipo().equals("huevo")){
            precio.setText("Precio: $"+ verTodoModel.getPrecio()+"/docena");
            totalPrecio = verTodoModel.getPrecio()* totalCantidad;
        }
        if(verTodoModel.getTipo().equals("lacteo")){
            precio.setText("Precio: $"+ verTodoModel.getPrecio()+"/unidad");
            totalPrecio = verTodoModel.getPrecio()* totalCantidad;
        }

        agregarCarrito = findViewById(R.id.add_to_cart);

        //Agregar producto al carrito
        agregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregadoCarrito();

            }
        });

        //Subir cantidad de producto
        agregarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalCantidad <10){
                    totalCantidad++;
                    cantidad.setText(String.valueOf(totalCantidad));
                    totalPrecio = verTodoModel.getPrecio()* totalCantidad;
                }

            }
        });

        //Restar cantidad de producto
        removerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalCantidad >1){
                    totalCantidad--;
                    cantidad.setText(String.valueOf(totalCantidad));
                    totalPrecio = verTodoModel.getPrecio()* totalCantidad;
                }

            }
        });
    }

    private void agregadoCarrito() {

        String guardarFecha, guardarHora;
        Calendar calendar = Calendar.getInstance();

        //Agregar y Dar formato a la fecha
        SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
        guardarFecha = fecha.format(calendar.getTime());

        //Agregar y dar formato hora
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss a");
        guardarHora = hora.format(calendar.getTime());

        //Almacenar datos en un Map
        final HashMap<String, Object> carritoMap = new HashMap<>();

        carritoMap.put("productoNombre", verTodoModel.getNombre());
        carritoMap.put("productoPrecio", precio.getText().toString());
        carritoMap.put("fecha", guardarFecha);
        carritoMap.put("hora", guardarHora);
        carritoMap.put("totalCantidad", cantidad.getText().toString());
        carritoMap.put("totalPrecio", totalPrecio);

        //Crear la Coleccion "AgregarCarrito", dentro de ella la coleccion "UsuarioActual" y ahi almacena
        //los datos del Map
        firestore.collection("UsuarioActual").document(auth.getCurrentUser().getUid())
                .collection("AgregarCarrito").add(carritoMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetalleActivity.this, "Agregado al Carrito", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }


}