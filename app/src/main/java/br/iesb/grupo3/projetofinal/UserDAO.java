package br.iesb.grupo3.projetofinal;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 1614290077 on 08/06/2018.
 */

public class UserDAO {
    private SQLiteDatabase database;
    private BaseDAO baseDAO;

    public UserDAO(Context context) {
        baseDAO = new BaseDAO(context);
    }

    public String newUser(String nome, String email, String senha, String confirmsenha) {

        ContentValues values = new ContentValues();
        long resultado;

        database = baseDAO.getWritableDatabase();
        values.put(BaseDAO.campo_nome, nome);
        values.put(BaseDAO.campo_email, email);
        values.put(BaseDAO.campo_senha, senha);
        values.put(BaseDAO.campo_confirmsenha, confirmsenha);

        resultado = database.insert(BaseDAO.tabela_contato, null, values);
        database.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";

        }
    }
}

