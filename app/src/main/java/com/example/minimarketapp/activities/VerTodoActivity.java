package com.example.minimarketapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.minimarketapp.R;
import com.example.minimarketapp.adapters.VerTodoAdapter;
import com.example.minimarketapp.models.VerTodoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class VerTodoActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    VerTodoAdapter verTodoAdapter;
    List<VerTodoModel> verTodoModelList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_todo);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();

        //Traer datos y rellenarlos en el recycleView
        String tipo = getIntent().getStringExtra("tipo");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        verTodoModelList = new ArrayList<>();
        verTodoAdapter = new VerTodoAdapter(this, verTodoModelList);
        recyclerView.setAdapter(verTodoAdapter);

        //Traer del Firestore segun el tipo
        //Fruta
        if(tipo != null && tipo.equalsIgnoreCase("fruta")){
            firestore.collection("TodoProductos").whereEqualTo("tipo", "fruta").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                        verTodoModelList.add(verTodoModel);
                        verTodoAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        //Verdura
        if(tipo != null && tipo.equalsIgnoreCase("verdura")){
            firestore.collection("TodoProductos").whereEqualTo("tipo", "verdura").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                        verTodoModelList.add(verTodoModel);
                        verTodoAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        //Marino
        if(tipo != null && tipo.equalsIgnoreCase("marino")){
            firestore.collection("TodoProductos").whereEqualTo("tipo", "marino").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                        verTodoModelList.add(verTodoModel);
                        verTodoAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        //Huevo
        if(tipo != null && tipo.equalsIgnoreCase("huevo")){
            firestore.collection("TodoProductos").whereEqualTo("tipo", "huevo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                        verTodoModelList.add(verTodoModel);
                        verTodoAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        //Lacteo
        if(tipo != null && tipo.equalsIgnoreCase("lacteo")){
            firestore.collection("TodoProductos").whereEqualTo("tipo", "lacteo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        VerTodoModel verTodoModel = documentSnapshot.toObject(VerTodoModel.class);
                        verTodoModelList.add(verTodoModel);
                        verTodoAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }
}