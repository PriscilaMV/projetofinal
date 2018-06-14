package br.iesb.grupo3.projetofinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.iesb.grupo3.projetofinal.BaseDAO;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class CadastrarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        Button btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO crud = new UserDAO(getBaseContext());
                EditText nome = (EditText) findViewById(R.id.txNome);
                EditText email = (EditText) findViewById(R.id.txtEmail);
                EditText senha = (EditText) findViewById(R.id.txSenha);
                EditText confirmSenha = (EditText) findViewById(R.id.confirmSenha);
                String nomeString = nome.getText().toString();
                String emailString = email.getText().toString();
                String senhaString = senha.getText().toString();
                String confirmSenhaString = confirmSenha.getText().toString();
                String resultado;

                resultado = crud.newUser(nomeString, emailString, senhaString, confirmSenhaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }

}

