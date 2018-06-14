package br.iesb.grupo3.projetofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        Button btSalvar = (Button) findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText nome = (EditText) findViewById(R.id.txNome);
                EditText email = (EditText) findViewById(R.id.txtEmail);
                EditText senha = (EditText) findViewById(R.id.txSenha);
                EditText confirmSenha = (EditText) findViewById(R.id.confirmSenha);
                String nomeString = nome.getText().toString();
                String emailString = email.getText().toString();
                String senhaString = senha.getText().toString();
                String confirmSenhaString = confirmSenha.getText().toString();
                String resultado;

                resultado = crud.insereDado(nomeString, emailString, senhaString, confirmSenhaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }

}

