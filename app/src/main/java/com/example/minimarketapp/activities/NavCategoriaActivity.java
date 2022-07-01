package com.example.minimarketapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.minimarketapp.R;
import com.example.minimarketapp.adapters.NavCategoriaDetalleAdapter;
import com.example.minimarketapp.models.HomeCategoria;
import com.example.minimarketapp.models.NavCategoriaDetalleModel;
import com.example.minimarketapp.models.VerTodoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoriaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoriaDetalleModel> list;
    NavCategoriaDetalleAdapter adapter;

    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_categoria);

        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NavCategoriaDetalleAdapter(this,list);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);



        //Traer datos y rellenarlos en el recycleView
        String tipo = getIntent().getStringExtra("tipo");

        //Fruta
        if(tipo != null && tipo.equalsIgnoreCase("bebida")){
            db.collection("NavCategoriaDetalle").whereEqualTo("tipo", "bebida").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoriaDetalleModel verTodoModel = documentSnapshot.toObject(NavCategoriaDetalleModel.class);
                        list.add(verTodoModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                }
            });
        }


    }
}