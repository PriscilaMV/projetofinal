package br.iesb.grupo3.projetofinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.iesb.grupo3.projetofinal.R;

public class CadastrarActivity extends AppCompatActivity {

    private EditText txEmail;
    private EditText txSenha;
    private EditText txConfirmSenha;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        txEmail = findViewById(R.id.txEmail);
        txSenha = findViewById(R.id.txSenha);
        txConfirmSenha = findViewById(R.id.txConfirmSenha);
        firebaseAuth = FirebaseAuth.getInstance();


        Button btnCancelar = findViewById(R.id.btCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnCadastrar = findViewById(R.id.btSalvar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(txEmail.getText().toString().equals("") || txSenha.getText().toString().equals("")|| txConfirmSenha.getText().toString().equals("")){
                    Toast.makeText(CadastrarActivity.this,"Todos os campos são obrigatórios!",Toast.LENGTH_LONG).show();

                }else{
                    registrarUser();
                    finish();
                }
            }
        });

    }
    private void registrarUser(){
        firebaseAuth.createUserWithEmailAndPassword(txEmail.getText().toString(),txSenha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            Toast.makeText(CadastrarActivity.this,"Cadastro efetuado com sucesso!",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(CadastrarActivity.this,"Não foi possível efetuar o cadastro!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
