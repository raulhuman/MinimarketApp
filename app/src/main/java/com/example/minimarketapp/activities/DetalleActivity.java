package com.example.minimarketapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.minimarketapp.R;
import com.example.minimarketapp.models.VerTodoModel;

public class DetalleActivity extends AppCompatActivity {

    ImageView detalleImg;
    TextView precio, rating, descripcion;
    Button agregarCarrito;
    ImageView agregarItem, removerItem;
    Toolbar toolbar;

    VerTodoModel verTodoModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Traer lo datos y guardarlos en un Object
        final Object object = getIntent().getSerializableExtra("detalle");
        if(object instanceof VerTodoModel){
            verTodoModel = (VerTodoModel) object;
        }

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
        }

        if(verTodoModel.getTipo().equals("huevo")){
            precio.setText("Precio: $"+ verTodoModel.getPrecio()+"/docena");
        }
        if(verTodoModel.getTipo().equals("lacteo")){
            precio.setText("Precio: $"+ verTodoModel.getPrecio()+"/unidad");
        }

        agregarCarrito = findViewById(R.id.add_to_cart);
    }


}