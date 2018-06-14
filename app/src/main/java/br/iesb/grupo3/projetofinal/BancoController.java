package br.iesb.grupo3.projetofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 1614290077 on 08/06/2018.
 */

public class BancoController {
    private SQLiteDatabase database;
    private CriaBanco criaBanco;

    public BancoController(Context context) {
        criaBanco = new CriaBanco(context);
    }

    public String insereDado(String nome, String email, String senha, String confirmsenha) {

        ContentValues valores;
        long resultado;

        database = criaBanco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.EMAIL, email);
        valores.put(CriaBanco.SENHA, senha);
        valores.put(CriaBanco.CONFIRMSENHA, confirmsenha);

        resultado = database.insert(CriaBanco.TABELA, null, valores);
        database.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";

        }
    }
}

