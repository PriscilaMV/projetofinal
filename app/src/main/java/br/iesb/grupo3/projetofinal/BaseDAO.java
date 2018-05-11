package br.iesb.grupo3.projetofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1614290077 on 11/05/2018.
 */

public class BaseDAO extends SQLiteOpenHelper {
    public static final String nome_banco= "usuarios";

    public static final int VERSAO_SCHEMA = 1;


    public static final String tabela_contato = "usuario";

    //Nome dos campos
    public static final String campo_id = "_id";
    public static final String campo_nome = "nome";
    public static final String campo_email = "email";
    public static final String campo_senha = "senha";
    public static final String campo_confirmsenha = "confirmsenha";

    private static final String criar_tabela_contato = "CREATE TABLE "
            + tabela_contato + " (" + campo_id + " INTEGER PRIMARY KEY, "
            + campo_nome + " TEXT, "
            + campo_email + "TEXT,"
            + campo_senha + " TEXT, "
            + campo_confirmsenha + " TEXT)";

    public BaseDAO(Context context) {
        super(context, nome_banco, null, VERSAO_SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(criar_tabela_contato);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
