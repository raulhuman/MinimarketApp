package com.example.minimarketapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minimarketapp.activities.DetalleActivity;
import com.example.minimarketapp.activities.PedidoRealizadoActivity;
import com.example.minimarketapp.adapters.MiCarritoAdapter;
import com.example.minimarketapp.models.MiCarritoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MiCarritoFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView ovetTotalAcumulado;
    ProgressBar progressBar;
    Button buyNow;

    RecyclerView recyclerView;
    MiCarritoAdapter carritoAdapter;
    List<MiCarritoModel> carritoModelList;


    public MiCarritoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mi_carrito, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        buyNow = root.findViewById(R.id.buy_now);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);


        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setVisibility(View.GONE);

        ovetTotalAcumulado = root.findViewById(R.id.textView7);

        // es un bus de eventos en toda la aplicaci??n y abarca las infracciones de capa en tu app;
        // cualquier componente puede escuchar eventos de cualquier otro componente.
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("MiTotalAcumulado"));

        //Crea una lista y lo llena de la Coleccion obtenia de FirebaseFirestone
        carritoModelList = new ArrayList<>();
        carritoAdapter = new MiCarritoAdapter(getActivity(), carritoModelList);
        recyclerView.setAdapter(carritoAdapter);

        db.collection("UsuarioActual").document(auth.getCurrentUser().getUid())
                .collection("AgregarCarrito").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                //Obtener el id del producto
                                String documentoId = documentSnapshot.getId();
                                //Trae el objeto
                                MiCarritoModel carritoModel = documentSnapshot.toObject(MiCarritoModel.class);

                                carritoModel.setDocumentId(documentoId);
                                carritoModelList.add(carritoModel);
                                carritoAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

        //Enviar datos del carrito al hacer clic a otro activity
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PedidoRealizadoActivity.class);
                intent.putExtra("itemLista", (Serializable) carritoModelList);
                startActivity(intent);
            }
        });

        return root;
    }

    //destinado a recibir y responder ante eventos globales generados por el sistema
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int cuentaTotal = intent.getIntExtra("totalAcumulado", 0);
            ovetTotalAcumulado.setText("Cuenta Total: "+cuentaTotal+"$");
        }
    };
}