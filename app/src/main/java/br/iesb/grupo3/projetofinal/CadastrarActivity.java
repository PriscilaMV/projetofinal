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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class CadastrarActivity extends AppCompatActivity {
    ProgressDialog progressCadastro;
    EditText etNome;
    EditText etEmail;
    EditText etSenha;
    EditText etConfirmsenha;

    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        userDAO = new UserDAO(this);

        etNome = (EditText) findViewById(R.id.txNome);
        etEmail= (EditText) findViewById(R.id.txEmail) ;
        etSenha = (EditText) findViewById(R.id.txSenha);
        etConfirmsenha = (EditText) findViewById(R.id.confirmSenha);

        Button btCancelar = (Button) findViewById(R.id.btCancelar);
        Button btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setText(R.string.action_register);
        progressCadastro = new ProgressDialog(this);
        btSalvar.setOnClickListener(this);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                limparTela();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String nome = "";
                String email = "";
                String senha = "";
                String confirmsenha = "";

                nome = etNome.getText().toString();
                email = etEmail.getText().toString();
                senha = etSenha.getText().toString();
                confirmsenha = etConfirmsenha.getText().toString();

                User usuario = new User(nome, email, senha, confirmsenha);

                userDAO.open();

                userDAO.novoUser(usuario);

                userDAO.close();

                limparTela();
            }
        });
    }

    private void limparTela() {
        etNome.setText("");
        etEmail.setText("");
        etSenha.setText("");
        etConfirmsenha.setText("");
    }
    public void salvar(){

        String nome= etNome.getText().toString().trim();
        String email  = etEmail.getText().toString().trim();
        if(TextUtils.isEmpty(nome)){
            Toast.makeText(this, R.string.enter_your_email,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.enter_your_password,Toast.LENGTH_LONG).show();
            return;
        }
        progressLogin.setMessage(getString(R.string.registering_user));
        progressLogin.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, R.string.successfully_registred,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("nome", etNome.getText().toString().trim());
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegistrationActivity.this, R.string.regristration_error,Toast.LENGTH_LONG).show();
                        }
                        progressLogin.dismiss();
                    }
                });
    }
        if(etNome.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.nome_obrigatorio));

            editTextNome.requestFocus();
        }
        else if(editTextEndereco.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.endereco_obrigatorio));

            editTextEndereco.requestFocus();

        }
        else if(!radioButtonMasculino.isChecked() && !radioButtonFeminino.isChecked()){

            Uteis.Alert(this, this.getString(R.string.sexo_obrigatorio));
        }
        else if(editTextDataNascimento.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.data_nascimento_obrigatorio));

            editTextDataNascimento.requestFocus();

        }
        else{


            /*CRIANDO UM OBJETO PESSOA*/
            PessoaModel pessoaModel = new PessoaModel();

            /*SETANDO O VALOR DO CAMPO NOME*/
            pessoaModel.setNome(editTextNome.getText().toString().trim());

            /*SETANDO O ENDEREÇO*/
            pessoaModel.setEndereco(editTextEndereco.getText().toString().trim());

            /*SETANDO O SEXO*/
            if(radioButtonMasculino.isChecked())
                pessoaModel.setSexo("M");
            else
                pessoaModel.setSexo("F");

            /*SETANDO A DATA DE NASCIMENTO*/
            pessoaModel.setDataNascimento(editTextDataNascimento.getText().toString().trim());

            /*REALIZANDO UM CAST PARA PEGAR O OBJETO DO ESTADO CIVIL SELECIONADO*/
            EstadoCivilModel estadoCivilModel = (EstadoCivilModel)spinnerEstadoCivil.getSelectedItem();

            /*SETANDO ESTO CIVIL*/
            pessoaModel.setEstadoCivil(estadoCivilModel.getCodigo());


            /*SETA O REGISTRO COMO INATIVO*/
            pessoaModel.setRegistroAtivo((byte)0);

            /*SE TIVER SELECIONADO SETA COMO ATIVO*/
            if(checkBoxRegistroAtivo.isChecked())
                pessoaModel.setRegistroAtivo((byte)1);

            /*SALVANDO UM NOVO REGISTRO*/
            new PessoaRepository(this).Salvar(pessoaModel);

            /*MENSAGEM DE SUCESSO!*/
            Uteis.Alert(this,this.getString(R.string.registro_salvo_sucesso));

            LimparCampos();
        }


    }

}

