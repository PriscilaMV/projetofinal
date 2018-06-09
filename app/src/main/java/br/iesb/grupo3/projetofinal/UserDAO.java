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

    public void open() throws SQLException {
        database = baseDAO.getWritableDatabase();
    }

    public void close() {
        baseDAO.close();
    }

    public long novoUser(User user) {
        ContentValues values = new ContentValues();

        values.put(BaseDAO.campo_id, user.getId());
        values.put(BaseDAO.campo_nome, user.getNome());
        values.put(BaseDAO.campo_email , user.getEmail());
        values.put(BaseDAO.campo_senha, user.getSenha());
        values.put(BaseDAO.campo_confirmsenha, user.getConfirmsenha());


        return database.insert(BaseDAO.tabela_contato, null, values);
    }

    public List<User> lerUsuarios() {
        Cursor c = database.rawQuery("SELECT * FROM " + BaseDAO.tabela_contato, null);
        List<User> usuarios = new ArrayList<User>();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                User user = new User(
                        c.getLong(c.getColumnIndex(BaseDAO.campo_id)),
                        c.getString(c.getColumnIndex(BaseDAO.campo_nome)),
                        c.getString(c.getColumnIndex(BaseDAO.campo_email)),
                        c.getString(c.getColumnIndex(BaseDAO.campo_senha)),
                        c.getString(c.getColumnIndex(BaseDAO.campo_confirmsenha)));
                usuarios.add(user);
                c.moveToNext();
            }
        }
        c.close();

        return usuarios;
    }


    }

