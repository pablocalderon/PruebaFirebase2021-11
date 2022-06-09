package cl.isisur.pruebafirebase2021_11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cl.isisur.pruebafirebase2021_11.Modelo.Autor;

public class MainActivity extends AppCompatActivity {


    private List<Autor> ListAutor = new ArrayList<Autor>();
    private List<String> ListaString = new ArrayList<String>();
    ArrayAdapter<Autor> arrayAdapterAutor;
    ArrayAdapter<String> arrayAdapterString;

    EditText eTNombre, eTCategoria, etCorreo;
    Button btAceptar;
    ListView lvAutorLista;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTNombre = findViewById(R.id.eTNombre);
        eTCategoria= findViewById(R.id.eTCategoria);
        etCorreo=findViewById(R.id.eTCorreo);
        btAceptar=findViewById(R.id.bTAceptar);
        lvAutorLista=findViewById(R.id.ldListadoAutores);


        incializarFirebase();
        ListarDatos();

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Autor autor = new Autor();
                autor.setIdAutor(UUID.randomUUID().toString());
                //autor.setIdAutor("20-781-888-8");
                autor.setNombre(eTNombre.getText().toString());
                autor.setEstado(eTCategoria.getText().toString());
                autor.setCorreo(etCorreo.getText().toString());
                databaseReference.child("Autor").child(autor.getIdAutor()).setValue(autor);

                eTNombre.setText("");
                eTCategoria.setText("");
                etCorreo.setText("");

            }
        });


    }

    private void ListarDatos() {
       databaseReference.child("Autor").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               ListAutor.clear();
               for (DataSnapshot obsSh: snapshot.getChildren() ){

                   Autor autor=obsSh.getValue(Autor.class);
                   if (autor.getEstado().equals("php")) {
                       ListaString.add(autor.getNombre()+" "+autor.getEstado());
                       ListAutor.add(autor);
                       arrayAdapterString= new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,ListaString);
                       arrayAdapterAutor = new ArrayAdapter<Autor>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, ListAutor);
                       lvAutorLista.setAdapter(arrayAdapterString);
                       //lvAutorLista.setAdapter(arrayAdapterAutor);

                   }

               }


           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });



    }

    private void incializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}