package br.iesb.grupo3.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilActivity extends AppCompatActivity {
    private EditText nome;
    private EditText serie;
    private EditText matricula;
    private FirebaseAuth mAuth;
    private FirebaseDatabase data;
    private DatabaseReference dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mAuth = FirebaseAuth.getInstance();
        data = FirebaseDatabase.getInstance();
        dataref = data.getReference("iesb/alunos");


        nome = findViewById(R.id.txNome);
        serie = findViewById(R.id.txSerie);
        matricula = findViewById(R.id.txMatricula);

        Button btSalvar = (Button) findViewById(R.id.btSalvarPerfil);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravar();
                Toast.makeText(PerfilActivity.this,"Gavado com Sucesso!",Toast.LENGTH_LONG).show();
                finish();
            }
        });
       Button btMapa = findViewById(R.id.btMapa);
        btMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(PerfilActivity.this, activity_maps.class);
                startActivity(t);
            }
        });
    }

    public void gravar(){
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Usuario perfil = new Usuario();
        perfil.setEmail(user.getEmail());
        perfil.setNome(nome.getText().toString());
        perfil.setMatricula(Integer.parseInt(matricula.getText().toString()));
        perfil.setSerie(serie.getText().toString());

        dataref.child(user.getUid()).setValue(perfil);
    }

}
