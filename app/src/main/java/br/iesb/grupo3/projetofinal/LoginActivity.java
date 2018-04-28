package br.iesb.grupo3.projetofinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText txtEmail;
    private EditText txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();

        txtEmail= findViewById(R.id.txtEmail);
        txtSenha= findViewById(R.id.txtSenha);

        Button entrar= findViewById(R.id.entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        TextView esqueceu= findViewById(R.id.esqueceu);
        esqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(LoginActivity.this, RecuperarSenhaActivity.class);
                startActivity(t);
            }});

        TextView criar= findViewById(R.id.txtCriar);
        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent t = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivity(t);
            }});

    }

    public void login(){
        Task<AuthResult> taskLogin= mAuth.signInWithEmailAndPassword(txtEmail.getText().toString(),
                txtSenha.getText().toString());

        taskLogin.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"Não foi possível efetuar login", Toast.LENGTH_LONG).show();
                }else{
                    Intent it= new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(it);
                }
            }
        });

    }
}