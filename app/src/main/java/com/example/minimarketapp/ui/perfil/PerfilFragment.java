package com.example.minimarketapp.ui.perfil;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.minimarketapp.R;
import com.example.minimarketapp.models.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class PerfilFragment extends Fragment {

    CircleImageView perfilImg;
    EditText nombre, email, telefono, direccion;
    Button actualizar, logout;

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;


    public View onCreateView(@NonNull LayoutInflater inflater,
                                   ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        perfilImg = root.findViewById(R.id.profile_img);
        nombre = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        telefono = root.findViewById(R.id.profile_number);
        direccion = root.findViewById(R.id.profile_address);
        actualizar = root.findViewById(R.id.update);

        logout = root.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });


        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Glide.with(getContext()).load(userModel.getPerfilImg()).into(perfilImg);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        //Dar clic en la imagen de Perfil
        perfilImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagenPerfil.launch("image/*");



                /*
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);

                 */
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarPerfilUsuario();
            }
        });

        return root;
    }


    private void actualizarPerfilUsuario() {
    }

    /*Vieja Version
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData() !=null){
            Uri perfilUri = data.getData();
            perfilImg.setImageURI(perfilUri);
            final StorageReference reference = storage.getReference().child("foto_perfil")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(perfilUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Cargado", Toast.LENGTH_SHORT).show();
                }
            });
        }
        }

     */
    //Nueva Version
    ActivityResultLauncher<String> imagenPerfil = registerForActivityResult(
            new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {

                    Uri perfilUri = result;
                    perfilImg.setImageURI(perfilUri);

                    //Guarda la imagen en Firebase Storage
                    final StorageReference reference = storage.getReference().child("foto_perfil")
                            .child(FirebaseAuth.getInstance().getUid());

                    reference.putFile(perfilUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Cargado", Toast.LENGTH_SHORT).show();

                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //Guarda la el link de la imagen en Firebase Real Database
                                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                            .child("perfilImg").setValue(uri.toString());
                                    Toast.makeText(getContext(), "Foto de Pefil Cargado", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });
                }
            }
    );


}