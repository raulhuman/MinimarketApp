package com.example.minimarketapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimarketapp.R;
import com.example.minimarketapp.adapters.HomeAdapter;
import com.example.minimarketapp.adapters.PopularAdapters;
import com.example.minimarketapp.adapters.RecomendadoAdapter;
import com.example.minimarketapp.databinding.FragmentHomeBinding;
import com.example.minimarketapp.models.HomeCategoria;
import com.example.minimarketapp.models.PopularModel;
import com.example.minimarketapp.models.RecomendadoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularRec, homeCatRec, recomendadoRec;
    FirebaseFirestore db;

    //Insertar popular items

    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    //Home Categorias
    List<HomeCategoria> categoriaList;
    HomeAdapter homeAdapter;

    //Recomendados
    List<RecomendadoModel> recomendadoModelList;
    RecomendadoAdapter recomendadoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explore_rec);
        recomendadoRec = root.findViewById(R.id.recommended_rec);

        //Popular items
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);


        //Trae coleccion de Cloud Firestore
        db.collection("ProductosPopulares")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();
                            }

                        }else
                        {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        }

                });

        //Home Categoria
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoriaList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoriaList);
        homeCatRec.setAdapter(homeAdapter);


        //Trae coleccion de Cloud Firestore
        db.collection("HomeCategorias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeCategoria homeCategoria = document.toObject(HomeCategoria.class);
                                categoriaList.add(homeCategoria);
                                homeAdapter.notifyDataSetChanged();
                            }

                        }else
                        {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });

        //Recomendados Categoria
        recomendadoRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recomendadoModelList = new ArrayList<>();
        recomendadoAdapter = new RecomendadoAdapter(getActivity(),recomendadoModelList);
        recomendadoRec.setAdapter(recomendadoAdapter);


        //Trae coleccion de Cloud Firestore
        db.collection("Recomendado")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                RecomendadoModel recomendadoModel = document.toObject(RecomendadoModel.class);
                                recomendadoModelList.add(recomendadoModel);
                                recomendadoAdapter.notifyDataSetChanged();
                            }

                        }else
                        {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });

        return root;
    }


}