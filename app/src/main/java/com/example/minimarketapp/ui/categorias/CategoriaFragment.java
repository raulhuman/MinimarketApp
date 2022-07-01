package com.example.minimarketapp.ui.categorias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimarketapp.R;
import com.example.minimarketapp.adapters.NavCategoriaAdapter;
import com.example.minimarketapp.models.NavCategoriaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoriaFragment extends Fragment {

    FirebaseFirestore db;

    RecyclerView recyclerView;
    List<NavCategoriaModel> categoriaModelList;
    NavCategoriaAdapter navCategoriaAdapter;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categoria, container, false);

        recyclerView = root.findViewById(R.id.cat_rec);
        db = FirebaseFirestore.getInstance();
        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        //Popular items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        categoriaModelList = new ArrayList<>();
        navCategoriaAdapter = new NavCategoriaAdapter(getActivity(),categoriaModelList);
        recyclerView.setAdapter(navCategoriaAdapter);


        //Trae coleccion de Cloud Firestore
        db.collection("NavCategoria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NavCategoriaModel navCategoriaModel = document.toObject(NavCategoriaModel.class);
                                categoriaModelList.add(navCategoriaModel);
                                navCategoriaAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);

                            }

                        }else
                        {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }

                });

        return root;
    }

}